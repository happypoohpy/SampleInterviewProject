package jp.babyplus.android.di

import dagger.Subcomponent
import jp.babyplus.android.di.scope.FragmentScope

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

}
