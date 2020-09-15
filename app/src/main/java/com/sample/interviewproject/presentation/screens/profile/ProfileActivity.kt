package com.sample.interviewproject.presentation.screens.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.sample.interviewproject.R
import com.sample.interviewproject.common.VerticalSpaceItemDecoration
import com.sample.interviewproject.model.User
import com.sample.interviewproject.presentation.screens.about.AboutActivity
import com.sample.interviewproject.presentation.screens.friends.FriendsActivity
import com.sample.interviewproject.presentation.screens.profile.adapters.FeaturedFriendsAdapter
import com.sample.interviewproject.presentation.screens.profile.adapters.ShotsAdapter
import jp.babyplus.android.presentation.screens.BaseActivity
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.view_circle_image_view.*
import javax.inject.Inject


class ProfileActivity : BaseActivity(), ProfileMvpView {

    @Inject
    lateinit var presenter: ProfileMvpPresenter<ProfileMvpView>

    private lateinit var friendsAdapter: FeaturedFriendsAdapter
    private lateinit var shotsAdapter: ShotsAdapter

    override fun layoutId(): Int = R.layout.activity_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        val verticalSpaceDecoration = VerticalSpaceItemDecoration(resources.getDimension(R.dimen.items_space).toInt())
        rv_friends.addItemDecoration(verticalSpaceDecoration)
        friendsAdapter = FeaturedFriendsAdapter()
        rv_friends.adapter = friendsAdapter

        val gridLayoutManager = GridLayoutManager(this, 3)
        rv_shots.layoutManager = gridLayoutManager
        rv_shots.isNestedScrollingEnabled = false
        shotsAdapter = ShotsAdapter()
        rv_shots.adapter = shotsAdapter

        setSupportActionBar(toolbar)
        title = ""
        initListeners()

        presenter.attachView(this)
        presenter.onViewLoaded()
    }

    private fun initListeners() {
        tv_see_all.setOnClickListener {
            val intent = Intent(this, FriendsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                showAboutScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAboutScreen() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun displayProfile(user: User) {

        runOnUiThread {
            Glide.with(this)
                .load(user.avatarUrl)
                .circleCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(iv_avatar);
            view_avatar.backgroundTintList = ContextCompat.getColorStateList(this, R.color.black)

            tv_given_name.text = "${user.givenName} ${user.surname}"
            tv_location.text = user.location
            tv_about.text = user.about

            user.featuredFriends?.let { friendsAdapter.setFriends(it) }
            user.shots?.let { shotsAdapter.setShots(it) }
        }
    }
}