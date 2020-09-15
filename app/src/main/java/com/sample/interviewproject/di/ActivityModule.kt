package jp.babyplus.android.di

import androidx.appcompat.app.AppCompatActivity
import com.sample.interviewproject.presentation.screens.about.AboutMvpPresenter
import com.sample.interviewproject.presentation.screens.about.AboutMvpView
import com.sample.interviewproject.presentation.screens.about.AboutPresenter
import com.sample.interviewproject.presentation.screens.friends.FriendsMvpPresenter
import com.sample.interviewproject.presentation.screens.friends.FriendsMvpView
import com.sample.interviewproject.presentation.screens.friends.FriendsPresenter
import com.sample.interviewproject.presentation.screens.profile.ProfileMvpPresenter
import com.sample.interviewproject.presentation.screens.profile.ProfileMvpView
import com.sample.interviewproject.presentation.screens.profile.ProfilePresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    internal fun activity(): AppCompatActivity {
        return activity
    }

    @Provides
    internal fun providesProfilePresenter(presenter: ProfilePresenter<ProfileMvpView>) :
            ProfileMvpPresenter<ProfileMvpView> = presenter

    @Provides
    internal fun providesAboutPresenter(presenter: AboutPresenter<AboutMvpView>) :
            AboutMvpPresenter<AboutMvpView> = presenter

    @Provides
    internal fun providesFriendsPresenter(presenter: FriendsPresenter<FriendsMvpView>) :
            FriendsMvpPresenter<FriendsMvpView> = presenter
}
