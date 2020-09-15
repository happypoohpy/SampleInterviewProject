package jp.babyplus.android.glide

import android.content.Context
import android.util.Log

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.sample.interviewproject.BuildConfig
import com.sample.interviewproject.R

import java.io.InputStream
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient

@GlideModule
class BabyAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(Log.VERBOSE)
        }
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(context.resources.getInteger(R.integer.http_connect_timeout_sec).toLong(), TimeUnit.SECONDS)
                .readTimeout(context.resources.getInteger(R.integer.http_read_timeout_sec).toLong(), TimeUnit.SECONDS)
                .writeTimeout(context.resources.getInteger(R.integer.http_write_timeout_sec).toLong(), TimeUnit.SECONDS)
                .build()

        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
    }
}