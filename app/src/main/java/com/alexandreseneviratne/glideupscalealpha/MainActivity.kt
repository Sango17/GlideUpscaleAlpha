package com.alexandreseneviratne.glideupscalealpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.annotation.GlideExtension

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView1 = findViewById<ImageView>(R.id.img1)
        val imageView2 = findViewById<ImageView>(R.id.img2)
        val imageView3 = findViewById<ImageView>(R.id.img3)
        val imageView4 = findViewById<ImageView>(R.id.img4)

        setPhoto(imageView1)
        setPhoto(imageView2, isAlpha = true, isUpscale = true)
        setPhoto(imageView3, isUpscale = true)
        setPhoto(imageView4, isAlpha = true)
    }

    private fun setPhoto(imageView: ImageView, isAlpha: Boolean = false, isUpscale: Boolean = false) {
        when {
            isAlpha && !isUpscale ->  {
                Glide.with(this).load(getDrawable(R.drawable.beard))
                    .apply(RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView)
            }
            isUpscale && !isAlpha ->  {
                Glide.with(this).load(getDrawable(R.drawable.beard))
                    .apply(RequestOptions()
                        .fitCenter()
                        .override(Target.SIZE_ORIGINAL))
                    .into(imageView)
            }
            isAlpha && isUpscale -> {
                Glide.with(this).load(getDrawable(R.drawable.beard))
                    .apply(RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(Target.SIZE_ORIGINAL))
                    .into(imageView)
            }
            else -> {
                Glide.with(this).load(getDrawable(R.drawable.beard))
                .into(imageView)
            }
        }
    }
}