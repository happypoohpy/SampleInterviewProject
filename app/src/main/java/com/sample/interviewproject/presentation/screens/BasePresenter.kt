package jp.co.bbo.prod.businesscard.ui.base

import android.content.Intent
import android.os.Bundle

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<T : MvpView> : Presenter<T> {

    var mvpView: T? = null
        private set

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

    override fun onLoad(args: Bundle?) {
    }

    override fun onViewLoaded() {
    }

    override fun onViewResumed() {

    }

    override fun processPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    }

    override fun processActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onPressedBack(): Boolean {
        return false
    }

    private val isViewAttached: Boolean
        get() = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")

}

