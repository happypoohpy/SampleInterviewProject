package jp.babyplus.android.di

import com.sample.interviewproject.presentation.screens.about.AboutActivity
import com.sample.interviewproject.presentation.screens.friends.FriendsActivity
import com.sample.interviewproject.presentation.screens.profile.ProfileActivity
import dagger.Subcomponent
import jp.babyplus.android.di.scope.ActivityScope
import jp.babyplus.android.presentation.screens.BaseActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: BaseActivity)

    fun inject(activity: ProfileActivity)

    fun inject(activity: AboutActivity)

    fun inject(activity: FriendsActivity)

    operator fun plus(module: FragmentModule): FragmentComponent
}
