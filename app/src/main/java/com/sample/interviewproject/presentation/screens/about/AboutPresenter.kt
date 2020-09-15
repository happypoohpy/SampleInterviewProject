package com.sample.interviewproject.presentation.screens.about

import jp.co.bbo.prod.businesscard.ui.base.BasePresenter
import javax.inject.Inject

class AboutPresenter<T : AboutMvpView> @Inject
constructor() : BasePresenter<T>(), AboutMvpPresenter<T> {
}