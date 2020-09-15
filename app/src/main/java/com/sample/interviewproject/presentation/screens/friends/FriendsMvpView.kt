package com.sample.interviewproject.presentation.screens.friends

import com.sample.interviewproject.model.Friend
import jp.co.bbo.prod.businesscard.ui.base.MvpView

interface FriendsMvpView : MvpView {

    fun refreshList(friends: List<Friend>)
}