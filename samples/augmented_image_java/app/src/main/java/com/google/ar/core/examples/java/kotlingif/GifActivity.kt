package com.google.ar.core.examples.java.kotlingif

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.core.examples.java.StartActivity
import com.google.ar.core.examples.java.augmentedimage.R
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.android.synthetic.main.activity_ar.*
import kotlinx.android.synthetic.main.activity_main.*

class GifActivity : AppCompatActivity() {
    private val TAG = "GifArActivity"

    private val augmentedImageMap: MutableMap<AugmentedImage, AugmentedImageNode> = mutableMapOf()

    private lateinit var arFragment: ArFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.arFragment = supportFragmentManager.findFragmentById(R.id.arFrag) as ArFragment
        arFragment.arSceneView.scene.addOnUpdateListener(this::onUpdateFrame)



        var btn_go_java  = findViewById(R.id.btn_go_java) as Button

        btn_go_java.setOnClickListener {

            val nextIntent = Intent(this, StartActivity::class.java)
            startActivity(nextIntent)


        }



    }

    override fun onResume() {
        super.onResume()

        if (augmentedImageMap.isEmpty()) {
            fitToScan.visibility = View.VISIBLE
        }
    }

    private fun onUpdateFrame(frameTime: FrameTime) {
        val frame = arFragment.arSceneView.arFrame

        frame?.let {
            val updatedAugmentedImages = it.getUpdatedTrackables(AugmentedImage::class.java)
            updatedAugmentedImages.forEach { augmentedImage ->
                when (augmentedImage.trackingState) {
                    TrackingState.PAUSED -> {
                        val message = "백운광장역을 찾았습니다!!"
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                    TrackingState.TRACKING -> {
                        fitToScan.visibility = View.GONE

                        if (!augmentedImageMap.containsKey(augmentedImage)) {
                            val node = AugmentedImageNode(this)
                            node.setImageToNode(augmentedImage)
                            augmentedImageMap.put(augmentedImage, node)
                            arFragment.arSceneView.scene.addChild(node)
                        }
                    }
                    TrackingState.STOPPED -> {
                        augmentedImageMap.remove(augmentedImage)
                    }
                    else -> {
                        Log.d(TAG, "Unknown image state")
                    }
                }
            }
        }
    }
}
