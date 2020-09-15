package com.sample.interviewproject.presentation.helpers

import android.app.Activity
import android.os.Message
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import jp.babyplus.android.common.handler.PauseHandler

/**
 * Activity, Fragmentのライフサイクルに応じて、Fragmentの置き換えをするクラス。
 * Activity, Fragmentのライフサイクルによってはこの処理ができないため、処理できるライフサイクルになるまでまち、
 * 処理できるようになったら処理を開始する。
 */
class ReplaceFragmentHelper : LifecycleObserver {
    private val babyPauseHandler: BabyPauseHandler

    constructor(activity: FragmentActivity) {
        babyPauseHandler = BabyPauseHandler(FragmentManagerGettable.Activity(activity))
    }

    constructor(fragment: Fragment) {
        babyPauseHandler = BabyPauseHandler(FragmentManagerGettable.Fragment(fragment))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        babyPauseHandler.resume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        babyPauseHandler.pause()
    }

    /**
     * 指定したFragmentを置換する。
     * 本メソッドでFragmentを置換した場合、[Activity.onResume] 〜 [Activity.onPause] までの期間は
     * 即座にFragmentを置換するが、それ以外は[Activity.onResume]に遷移するまで置換を待つ。
     *
     * @param fragment        Fragment
     * @param containerViewId 置き換えるViewのID
     */
    fun replaceFragment(fragment: Fragment, @IdRes @LayoutRes containerViewId: Int) {
        replaceFragment(fragment, containerViewId, false)
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
    fun replaceFragment(fragment: Fragment, @IdRes @LayoutRes containerViewId: Int, justNow: Boolean) {
        val msg = Message.obtain()
        msg.obj = ReplaceFragmentHandlerObject(containerViewId, fragment, justNow)
        babyPauseHandler.sendMessage(msg)
    }

    /**
     * [Fragment.onResume] 〜 [Fragment.onPause] までの期間は即座に処理をし、
     * それ以外は[Fragment.onResume]に状態が変わるまで処理を待つHandler。
     */
    private class BabyPauseHandler(private val fragmentManagerGettable: FragmentManagerGettable) : PauseHandler() {
        override fun processMessage(message: Message) {
            val fragmentManager = fragmentManagerGettable.fragmentManager ?: return
            val handlerObject = (message.obj as? ReplaceFragmentHandlerObject) ?: return
            val transaction = fragmentManager
                    .beginTransaction()
                    .replace(
                            handlerObject.containerViewId,
                            handlerObject.fragment,
                            handlerObject.fragment.javaClass.simpleName)
            if (handlerObject.isJustNow) {
                transaction.commitNow()
            } else {
                transaction.commit()
            }
        }
    }

    /**
     * [BabyPauseHandler] に送る際のオブジェクト。
     * containerViewIdとfragmentのオブジェクトを格納する。
     */
    private data class ReplaceFragmentHandlerObject constructor(val containerViewId: Int, val fragment: Fragment, val isJustNow: Boolean)
}
