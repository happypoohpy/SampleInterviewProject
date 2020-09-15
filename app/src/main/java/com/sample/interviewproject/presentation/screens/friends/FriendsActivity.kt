package com.sample.interviewproject.presentation.screens.friends

import android.os.Bundle
import com.sample.interviewproject.R
import com.sample.interviewproject.model.Friend
import com.sample.interviewproject.presentation.screens.friends.adapters.FriendsAdapter
import com.sample.interviewproject.presentation.screens.profile.ProfileMvpPresenter
import com.sample.interviewproject.presentation.screens.profile.ProfileMvpView
import com.sample.interviewproject.presentation.screens.profile.adapters.FeaturedFriendsAdapter
import com.sample.interviewproject.presentation.screens.profile.adapters.ShotsAdapter
import jp.babyplus.android.presentation.screens.BaseActivity
import kotlinx.android.synthetic.main.activity_friends.*
import javax.inject.Inject

class FriendsActivity : BaseActivity(), FriendsMvpView {

    @Inject
    lateinit var presenter: FriendsMvpPresenter<FriendsMvpView>

    private lateinit var friendsAdapter: FriendsAdapter

    override fun layoutId(): Int = R.layout.activity_friends

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        friendsAdapter = FriendsAdapter()
        rv_friends.adapter = friendsAdapter

        initListeners()

        presenter.attachView(this)
        presenter.onViewLoaded()
    }

    fun initListeners() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun refreshList(friends: List<Friend>) {
        runOnUiThread {
            friendsAdapter.setFriends(friends)
        }
    }
}