package com.sample.interviewproject

import android.app.Activity
import android.app.NotificationManager
import android.content.ComponentCallbacks2
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import jp.babyplus.android.di.AppComponent
import jp.babyplus.android.di.AppModule
import jp.babyplus.android.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

open class SampleApplication : MultiDexApplication() {
    val module = AppModule(this)
    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(module)
            .build()
    }

    @Inject
    lateinit var babyActivityLifecycleCallbacks: BabyActivityLifecycleCallbacks

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
        initLeakCanary()
        initActivityLifecycleCallbacks()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            onBackground()
        }
    }

    /**
     * Activityスタックの中で、[MainActivity]が存在するかを取得する
     *
     * @return Activityのスタックの中で、[MainActivity]が存在する場合はtrue、それ以外はfalse
     */
    fun hasMainActivityInTasks(): Boolean {
        return babyActivityLifecycleCallbacks.hasMainActivityInTasks
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun initActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(babyActivityLifecycleCallbacks)
    }

    private fun onBackground() {
    }

    class BabyActivityLifecycleCallbacks @Inject constructor() : ActivityLifecycleCallbacks {

        private var mainActivityCount = 0
            set(value) {
                field = value
                Timber.tag(TAG).v("mainActivityCount is $value")
            }

        val hasMainActivityInTasks: Boolean
            get() = mainActivityCount > 0

        override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
            ++mainActivityCount
        }

        override fun onActivityStarted(activity: Activity?) {}

        override fun onActivityResumed(activity: Activity?) {}

        override fun onActivityPaused(activity: Activity?) {}

        override fun onActivityStopped(activity: Activity?) {}

        override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {}

        override fun onActivityDestroyed(activity: Activity?) {
            --mainActivityCount
        }
    }

    companion object {
        private const val TAG = "SampleActivityLifecycleCallbacks"
    }
}
