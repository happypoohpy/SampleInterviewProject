package com.sample.interviewproject.presentation.screens.profile

import android.view.View
import com.sample.interviewproject.api.service.SampleService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import jp.co.bbo.prod.businesscard.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class ProfilePresenter<T : ProfileMvpView> @Inject
constructor(
    val service: SampleService,
    val compositeDisposable: CompositeDisposable
) : BasePresenter<T>(), ProfileMvpPresenter<T> {

    override fun onViewLoaded() {
        loadProfile()
    }

    private fun loadProfile() {
        service.getProfile()
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                if (response == null) {
                    return@subscribe
                }

                if (response.code() == 503) {
                    return@subscribe
                }

                val body = response.body()
                if (body == null || !response.isSuccessful) {
                    return@subscribe
                }

                mvpView?.displayProfile(body)
            }, {
                Timber.e(it)
            })
            .addTo(compositeDisposable)
    }
}