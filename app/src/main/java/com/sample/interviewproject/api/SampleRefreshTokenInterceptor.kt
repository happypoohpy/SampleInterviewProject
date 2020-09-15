package jp.babyplus.android.api

import android.content.Context
import com.google.gson.Gson
import com.sample.interviewproject.R
import com.sample.interviewproject.api.service.SampleService
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SampleRefreshTokenInterceptor @Inject internal constructor(
        context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        private val gson: Gson
) : Interceptor {

    companion object {
        private const val TAG = "SampleRefreshTokenInterceptor"

        private val lock = Any()
    }

    private val sampleService: SampleService

    init {
        val dispatcher = Dispatcher()
        dispatcher.maxRequestsPerHost = 1

        val httpClient = OkHttpClient.Builder()
                .connectTimeout(context.resources.getInteger(R.integer.http_connect_timeout_sec).toLong(), TimeUnit.SECONDS)
                .readTimeout(context.resources.getInteger(R.integer.http_read_timeout_sec).toLong(), TimeUnit.SECONDS)
                .writeTimeout(context.resources.getInteger(R.integer.http_write_timeout_sec).toLong(), TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .addInterceptor(httpLoggingInterceptor)
                .build()
        val retrofit = Retrofit.Builder()
                .client(httpClient)
                .baseUrl(context.getString(R.string.api_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        sampleService = retrofit.create(SampleService::class.java)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}
