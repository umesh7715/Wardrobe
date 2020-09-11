package com.andromesh.my_portfolio.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andromesh.wardrobe.ui.viewmodel.ClothViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ClothViewModel::class)
    abstract fun bindClothViewModel(viewModel: ClothViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
