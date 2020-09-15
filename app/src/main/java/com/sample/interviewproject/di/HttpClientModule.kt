package com.sample.interviewproject.di

import android.content.Context
import com.sample.interviewproject.R
import dagger.Module
import dagger.Provides
import jp.babyplus.android.api.SampleRefreshTokenInterceptor
import okhttp3.Cache
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpClientModule {

    @Singleton
    @Provides
    internal fun provideHttpClient(context: Context,
                                   sampleRefreshTokenInterceptor: SampleRefreshTokenInterceptor,
                                   httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val cacheDir = File(context.cacheDir, CACHE_FILE_NAME)
        val cache = Cache(cacheDir, MAX_CACHE_SIZE)

        val dispatcher = Dispatcher()
        dispatcher.maxRequestsPerHost = 1

        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(context.resources.getInteger(R.integer.http_connect_timeout_sec).toLong(), TimeUnit.SECONDS)
                .readTimeout(context.resources.getInteger(R.integer.http_read_timeout_sec).toLong(), TimeUnit.SECONDS)
                .writeTimeout(context.resources.getInteger(R.integer.http_write_timeout_sec).toLong(), TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .addInterceptor(sampleRefreshTokenInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    companion object {
        private const val CACHE_FILE_NAME = "okhttp.cache"

        private const val MAX_CACHE_SIZE = (4 * 1024 * 1024).toLong() // 4MB
    }
}
