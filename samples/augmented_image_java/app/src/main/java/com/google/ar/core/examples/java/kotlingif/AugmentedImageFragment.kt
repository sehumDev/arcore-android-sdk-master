package com.google.ar.core.examples.java.kotlingif

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment
import java.io.IOException

class AugmentedImageFragment : ArFragment() {
    private val TAG = "AugmentedImageFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        planeDiscoveryController.apply {
            hide()
            setInstructionView(null)
        }
        arSceneView.planeRenderer.isEnabled = false

        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        if (!setupImageDatabase(config, session)) {
            Log.e(TAG, "Could not set up augmented image database")
            Toast.makeText(requireContext(), "Could not set up augmented image database", Toast.LENGTH_LONG).show()
        }
        config.focusMode = Config.FocusMode.AUTO
        config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        Log.d(TAG, "Focus Mode ${config.focusMode}")
        return config
    }

    private fun setupImageDatabase(config: Config, session: Session?): Boolean {
        val imageDatabase = AugmentedImageDatabase(session)
        return try {
            requireContext().assets.open("default03.jpg").use { inputStream ->
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageDatabase.addImage("default", bitmap, 0.12f)
            }

//            requireContext().assets.open("gandalf.png").use { inputStream ->
//                val bitmap = BitmapFactory.decodeStream(inputStream)
//                imageDatabase.addImage("gandalf", bitmap, 0.12f)
//            }

//            requireContext().assets.open("trampoline.png").use { inputStream ->
//                val bitmap = BitmapFactory.decodeStream(inputStream)
//                imageDatabase.addImage("trampoline", bitmap)
//            }

            config.augmentedImageDatabase = imageDatabase
            true
        } catch (e: IOException) {
            Log.e(TAG, "IO exception loading augmented image database.", e)
            false
        }
    }
}