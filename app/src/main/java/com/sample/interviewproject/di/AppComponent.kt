package jp.babyplus.android.di

import com.sample.interviewproject.SampleApplication
import com.sample.interviewproject.di.HttpClientModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, HttpClientModule::class])
interface AppComponent {
    fun inject(application: SampleApplication)

    operator fun plus(module: ActivityModule): ActivityComponent
}
