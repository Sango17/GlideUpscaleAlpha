package com.alexandreseneviratne.glideupscalealpha

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException

@GlideModule
class GlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        if (BuildConfig.DEBUG)
            builder.setLogLevel(Log.ERROR)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
    }
}