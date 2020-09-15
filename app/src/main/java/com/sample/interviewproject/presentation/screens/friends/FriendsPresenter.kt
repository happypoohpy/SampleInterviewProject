package com.sample.interviewproject.presentation.screens.friends

import com.sample.interviewproject.api.service.SampleService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import jp.co.bbo.prod.businesscard.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class FriendsPresenter<T : FriendsMvpView> @Inject
constructor(
    val service: SampleService,
    val compositeDisposable: CompositeDisposable
) : BasePresenter<T>(), FriendsMvpPresenter<T> {

    override fun onViewLoaded() {
        super.onViewLoaded()

        loadFriends()
    }

    private fun loadFriends() {
        service.getFriends()
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

                mvpView?.refreshList(body)
            }, {
                Timber.e(it)
            })
            .addTo(compositeDisposable)
    }
}