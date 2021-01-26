package com.alexandreseneviratne.glideupscalealpha

import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    private var mScale = 1f
    private var mScaleDetector: ScaleGestureDetector? = null
    var gestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setGestureListener()

        val imageView1 = findViewById<ImageView>(R.id.img1)
        val imageView2 = findViewById<ImageView>(R.id.img2)
        val imageView3 = findViewById<ImageView>(R.id.img3)
        val imageView4 = findViewById<ImageView>(R.id.img4)

        setPhoto(imageView1)
        setPhoto(imageView2, isAlpha = true, isUpscale = true)
        setPhoto(imageView3, isUpscale = true)
        setPhoto(imageView4)
    }

    private fun setPhoto(imageView: ImageView, isAlpha: Boolean = false, isUpscale: Boolean = false) {
        when {
            isAlpha && !isUpscale ->  {
                GlideApp.with(this).load(getDrawable(R.drawable.beard))
                    .apply(RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView)
            }
            isUpscale && !isAlpha ->  {
                GlideApp.with(this).load(getDrawable(R.drawable.beard))
                    .apply(RequestOptions()
                        .fitCenter()
                        .override(Target.SIZE_ORIGINAL))
                    .into(imageView)
            }
            isAlpha && isUpscale -> {
                GlideApp.with(this).load(getDrawable(R.drawable.beard))
                    .apply(RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(Target.SIZE_ORIGINAL))
                    .into(imageView)
            }
            else -> {
                GlideApp.with(this).load(getDrawable(R.drawable.beard))
                .into(imageView)
            }
        }
    }

    private fun setGestureListener() {
        gestureDetector = GestureDetector(this, GestureListener())

        mScaleDetector = ScaleGestureDetector(this, object : SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val scale = 1 - detector.scaleFactor
                val prevScale = mScale
                mScale += scale
                if (mScale < 0.1f) // Minimum scale condition:
                    mScale = 0.1f
                if (mScale > 10f) // Maximum scale condition:
                    mScale = 10f
                val scaleAnimation =
                    ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.focusX,
                        detector.focusY)
                scaleAnimation.duration = 0
                scaleAnimation.fillAfter = true
                val layout = findViewById<View>(R.id.container) as NestedScrollView
                layout.startAnimation(scaleAnimation)
                return true
            }
        })
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        super.dispatchTouchEvent(event)
        mScaleDetector!!.onTouchEvent(event)
        gestureDetector!!.onTouchEvent(event)
        return gestureDetector!!.onTouchEvent(event)
    }

    private class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        // event when double tap occurs
        override fun onDoubleTap(e: MotionEvent): Boolean {
            // double tap fired.
            return true
        }
    }
}