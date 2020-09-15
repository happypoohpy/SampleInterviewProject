package jp.co.bbo.prod.businesscard.ui.base

import android.content.Intent
import android.os.Bundle

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface Presenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

    fun onLoad(args: Bundle?)

    fun onViewLoaded()

    fun onViewResumed()

    fun processPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)

    fun processActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun onPressedBack() : Boolean
}
