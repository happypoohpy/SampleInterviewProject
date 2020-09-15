package jp.babyplus.android.presentation.screens

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sample.interviewproject.SampleApplication
import com.sample.interviewproject.presentation.helpers.ReplaceFragmentHelper
import jp.babyplus.android.di.ActivityComponent
import jp.babyplus.android.di.ActivityModule
import jp.co.bbo.prod.businesscard.ui.base.MvpView
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity(), MvpView {

    private val replaceFragmentHelper by lazy { ReplaceFragmentHelper(this) }

    @LayoutRes abstract fun layoutId(): Int

    val component: ActivityComponent by lazy {
        val babyApplication = application as SampleApplication
        babyApplication.component.plus(ActivityModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(TAG).v("%s.onCrate", javaClass.simpleName)

        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        lifecycle.addObserver(replaceFragmentHelper)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        Timber.tag(TAG).v("%s.onPostCreate", javaClass.simpleName)

        super.onPostCreate(savedInstanceState)
    }

    override fun onStart() {
        Timber.tag(TAG).v("%s.onStart", javaClass.simpleName)

        super.onStart()
    }

    override fun onRestart() {
        Timber.tag(TAG).v("%s.onRestart", javaClass.simpleName)

        super.onRestart()
    }

    override fun onResume() {
        Timber.tag(TAG).v("%s.onResume", javaClass.simpleName)

        super.onResume()
    }

    override fun onPostResume() {
        Timber.tag(TAG).v("%s.onPostResume", javaClass.simpleName)

        super.onPostResume()
    }

    override fun onPause() {
        Timber.tag(TAG).v("%s.onPause", javaClass.simpleName)

        super.onPause()
    }

    override fun onStop() {
        Timber.tag(TAG).v("%s.onStop", javaClass.simpleName)

        super.onStop()
    }

    override fun onDestroy() {
        Timber.tag(TAG).v("%s.onDestroy", javaClass.simpleName)

        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.tag(TAG).v("%s.onSaveInstanceState", javaClass.simpleName)

        super.onSaveInstanceState(outState)
    }

    override fun onNewIntent(intent: Intent) {
        Timber.tag(TAG).v("%s.onNewIntent", javaClass.simpleName)

        super.onNewIntent(intent)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Timber.tag(TAG).v("%s.onConfigurationChanged", javaClass.simpleName)

        super.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun initBackToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    protected fun replaceFragment(fragment: Fragment, @IdRes @LayoutRes containerViewId: Int) {
        replaceFragmentHelper.replaceFragment(fragment, containerViewId)
    }

    protected fun replaceFragment(fragment: Fragment, @IdRes @LayoutRes containerViewId: Int, justNow: Boolean) {
        replaceFragmentHelper.replaceFragment(fragment, containerViewId, justNow)
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}
