package com.sample.interviewproject.presentation.helpers

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference

sealed class FragmentManagerGettable {
    abstract val fragmentManager: FragmentManager?

    class Activity(activity: FragmentActivity) : FragmentManagerGettable() {
        private var reference: WeakReference<FragmentActivity> = WeakReference(activity)

        override val fragmentManager
            get() = reference.get()?.supportFragmentManager
    }

    class Fragment(fragment: androidx.fragment.app.Fragment) : FragmentManagerGettable() {
        private var reference: WeakReference<androidx.fragment.app.Fragment> = WeakReference(fragment)

        override val fragmentManager
            get() = reference.get()?.fragmentManager
    }
}
