package com.andromesh.my_portfolio.di


import com.andromesh.wardrobe.ui.fragments.MyFavourites
import com.andromesh.wardrobe.ui.fragments.WardeobeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMyFavourites(): MyFavourites

    @ContributesAndroidInjector
    abstract fun contributeWardeobeFragment(): WardeobeFragment

}
