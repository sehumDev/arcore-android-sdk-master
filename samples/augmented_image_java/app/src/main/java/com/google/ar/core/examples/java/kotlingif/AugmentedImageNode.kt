package com.google.ar.core.examples.java.kotlingif

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.ar.core.AugmentedImage
import com.google.ar.core.Pose
import com.google.ar.core.examples.java.augmentedimage.R
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.DpToMetersViewSizer
import com.google.ar.sceneform.rendering.FixedHeightViewSizer
import com.google.ar.sceneform.rendering.FixedWidthViewSizer
import com.google.ar.sceneform.rendering.ViewRenderable
import java.util.concurrent.CompletableFuture

class AugmentedImageNode(context: Context) : AnchorNode() {
    private val TAG = "AugmentedImageNode"

    private val gifViewLoader: CompletableFuture<ViewRenderable> = ViewRenderable
        .builder()
        .setView(context, R.layout.image_item)
        .setVerticalAlignment(ViewRenderable.VerticalAlignment.CENTER)
        .build()
    private var gifView: ViewRenderable? = null

    private lateinit var image: AugmentedImage




    init {
        gifViewLoader.thenAccept { viewRenderable ->
            run {
                gifView = viewRenderable
            }
        }
    }

    fun setImageToNode(image: AugmentedImage) {
        this.image = image

        if (!gifViewLoader.isDone) {
            gifViewLoader
                .thenAccept {
                    gifView = it
                    setImageToNode(image)
                }
            gifViewLoader.exceptionally { throwable: Throwable ->
                Log.e("AugmentedImageNode", "failed to load", throwable)
                null
            }
            return
        }

        anchor = image.createAnchor(image.centerPose)

        // TODO Get actual width of image dynamically, could just be luck that GIPHY images are 400 pixels wide
        val imageWidth = 400
        val viewWidth = (imageWidth / image.extentX).toInt()
        gifView?.sizer = DpToMetersViewSizer(viewWidth)


        Log.d(TAG, "extentX ${image.extentX}, extentZ ${image.extentZ}")


        val pose = Pose.makeTranslation(0.0f, 0.0f, 0.0f)
        val localPosition = Vector3(pose.tx(), pose.ty(), pose.tz())
        val centerNode = Node()
        centerNode.setParent(this)
        centerNode.localPosition = localPosition
        centerNode.localRotation = Quaternion(pose.qx(), 90f, -90f, pose.qw())
        centerNode.renderable = gifView

        gifView?.view?.let { view ->
            val imageView = view.findViewById<ImageView>(R.id.imageView)

            val imageUri = when (image.name) {
                //"gandalf" -> Uri.parse("https://media.giphy.com/media/TcdpZwYDPlWXC/giphy.gif")


                    "default" -> Uri.parse("https://media.giphy.com/media/coK5nWbgOir0An4q02/source.gif")
               // "default" -> Uri.parse("https://ezgif.com/images/bg-transparent.gif")

                //"trampoline" -> Uri.parse("https://media.giphy.com/media/ToMjGpjwk1MxyYRcQnK/giphy.gif")
                else -> null
            }

            Glide.with(view.context)
                .load(imageUri)
                .into(imageView)
        }


    }
}