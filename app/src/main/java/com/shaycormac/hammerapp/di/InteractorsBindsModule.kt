package com.shaycormac.hammerapp.di

import com.shaycormac.hammerapp.domain.interactors.MealsInteractor
import com.shaycormac.hammerapp.domain.interactors.MealsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InteractorsBindsModule {

    @Binds
    abstract fun bindMealsInteractor(
        mealsInteractorImpl: MealsInteractorImpl
    ): MealsInteractor

}
