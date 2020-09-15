package com.sample.interviewproject.presentation.screens.about

import android.os.Bundle
import android.view.*
import com.sample.interviewproject.BuildConfig
import com.sample.interviewproject.R
import com.sample.interviewproject.presentation.screens.profile.ProfileMvpPresenter
import com.sample.interviewproject.presentation.screens.profile.ProfileMvpView
import jp.babyplus.android.presentation.screens.BaseActivity
import jp.babyplus.android.presentation.screens.BaseFragment
import kotlinx.android.synthetic.main.activity_about.*
import javax.inject.Inject

class AboutActivity : BaseActivity(), AboutMvpView {

    @Inject
    lateinit var presenter: AboutMvpPresenter<AboutMvpView>

    override fun layoutId(): Int = R.layout.activity_about

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        tv_version.text = getString(R.string.about_version_format, BuildConfig.VERSION_NAME)
        setSupportActionBar(toolbar)
        title = ""

        presenter.attachView(this)
        presenter.onViewLoaded()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_close -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}