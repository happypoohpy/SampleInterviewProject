package jp.babyplus.android.presentation.screens

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sample.interviewproject.presentation.helpers.ReplaceFragmentHelper
import jp.babyplus.android.di.FragmentComponent
import jp.babyplus.android.di.FragmentModule
import jp.co.bbo.prod.businesscard.ui.base.MvpView
import timber.log.Timber

abstract class BaseFragment : Fragment(), MvpView {

    private val replaceFragmentHelper by lazy { ReplaceFragmentHelper(this) }

    val component: FragmentComponent by lazy {
        val activity = activity as? BaseActivity
                ?: throw IllegalStateException("The activity of this fragment is not an instance of BaseActivity")
        activity.component.plus(FragmentModule(this))
    }

    override fun onAttach(context: Context) {
        Timber.tag(TAG).v("%s.onAttach", javaClass.simpleName)

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.tag(TAG).v("%s.onCreate", javaClass.simpleName)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.tag(TAG).v("%s.onCreateView", javaClass.simpleName)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.tag(TAG).v("%s.onViewCreated", javaClass.simpleName)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.tag(TAG).v("%s.onActivityCreated", javaClass.simpleName)

        super.onActivityCreated(savedInstanceState)

        lifecycle.addObserver(replaceFragmentHelper)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Timber.tag(TAG).v("%s.onViewStateRestored", javaClass.simpleName)

        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Timber.tag(TAG).v("%s.onStart", javaClass.simpleName)

        super.onStart()
    }

    override fun onResume() {
        Timber.tag(TAG).v("%s.onResume", javaClass.simpleName)

        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.tag(TAG).v("%s.onSaveInstanceState", javaClass.simpleName)

        super.onSaveInstanceState(outState)
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        Timber.tag(TAG).v("%s.onMultiWindowModeChanged", javaClass.simpleName)

        super.onMultiWindowModeChanged(isInMultiWindowMode)
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        Timber.tag(TAG).v("%s.onPictureInPictureModeChanged", javaClass.simpleName)

        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Timber.tag(TAG).v("%s.onConfigurationChanged", javaClass.simpleName)

        super.onConfigurationChanged(newConfig)
    }

    override fun onPause() {
        Timber.tag(TAG).v("%s.onPause", javaClass.simpleName)

        super.onPause()
    }

    override fun onStop() {
        Timber.tag(TAG).v("%s.onStop", javaClass.simpleName)

        super.onStop()
    }

    override fun onLowMemory() {
        Timber.tag(TAG).v("%s.onLowMemory", javaClass.simpleName)

        super.onLowMemory()
    }

    override fun onDestroyView() {
        Timber.tag(TAG).v("%s.onDestroyView", javaClass.simpleName)

        super.onDestroyView()
    }

    override fun onDestroy() {
        Timber.tag(TAG).v("%s.onDestroy", javaClass.simpleName)

        super.onDestroy()
    }

    /**
     * 指定したFragmentを置換する。
     * 本メソッドでFragmentを置換した場合、[Activity.onResume] 〜 [Activity.onPause] までの期間は
     * 即座にFragmentを置換するが、それ以外は[Activity.onResume]に遷移するまで置換を待つ。
     *
     * @param fragment        Fragment
     * @param containerViewId 置き換えるViewのID
     */
    protected fun replaceFragment(fragment: Fragment, @IdRes @LayoutRes containerViewId: Int) {
        replaceFragmentHelper.replaceFragment(fragment, containerViewId)
    }

    /**
     * 指定したFragmentを置換する。
     * 本メソッドでFragmentを置換した場合、[Activity.onResume] 〜 [Activity.onPause] までの期間は
     * 即座にFragmentを置換するが、それ以外は[Activity.onResume]に遷移するまで置換を待つ。
     *
     * @param fragment        Fragment
     * @param containerViewId 置き換えるViewのID
     * @param justNow         即座に反映するかどうか。trueの場合 [FragmentTransaction.commitNow] でFragmentを置き換える。
     */
    protected fun replaceFragment(fragment: Fragment, @IdRes @LayoutRes containerViewId: Int, justNow: Boolean) {
        replaceFragmentHelper.replaceFragment(fragment, containerViewId, justNow)
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}
