package jp.babyplus.android.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    internal fun provideFragmentManager(): FragmentManager? {
        return fragment.fragmentManager

    }
}
