package com.shaycormac.hammerapp.di

import com.shaycormac.hammerapp.data.MealsRepositoryImpl
import com.shaycormac.hammerapp.domain.MealsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindsModule {
    @Binds
    @Singleton
    abstract fun bindMealsRepository(
        mealsRepositoryImpl: MealsRepositoryImpl
    ): MealsRepository
}
