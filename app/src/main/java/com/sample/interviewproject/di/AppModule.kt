package jp.babyplus.android.di

import android.app.Application
import android.content.Context
import com.google.gson.*
import com.sample.interviewproject.BuildConfig
import com.sample.interviewproject.R
import com.sample.interviewproject.api.service.SampleService
import com.sample.interviewproject.model.Friend
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(app: Application) {
    private val context: Context

    init {
        context = app
    }

    @Provides
    internal fun provideContext(): Context {
        return context
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(context: Context, client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(context.getString(R.string.api_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Singleton
    @Provides
    @Named("accept null field")
    fun provideAcceptNullFieldRetrofit(context: Context,
                                       client: OkHttpClient,
                                       @Named("accept null field") gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(context.getString(R.string.api_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Singleton
    @Provides
    internal fun provideSampleService(retrofit: Retrofit): SampleService {
        return retrofit.create(SampleService::class.java)
    }

    @Singleton
    @Provides
    @Named("accept null field")
    internal fun provideAcceptNullFieldSampleService(@Named("accept null field") retrofit: Retrofit): SampleService {
        return retrofit.create(SampleService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateSerializer())
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()
    }

    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    private class DateSerializer : JsonSerializer<Date> {
        override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src.time / 1000)
        }
    }

    private class DateDeserializer : JsonDeserializer<Date> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
            val value = json.asLong
            return Date(value * 1000)
        }
    }

}
