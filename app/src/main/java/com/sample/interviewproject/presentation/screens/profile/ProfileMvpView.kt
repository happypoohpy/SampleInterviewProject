package com.sample.interviewproject.presentation.screens.profile

import com.sample.interviewproject.model.User
import jp.co.bbo.prod.businesscard.ui.base.MvpView

interface ProfileMvpView : MvpView {

    fun displayProfile(user: User)
}