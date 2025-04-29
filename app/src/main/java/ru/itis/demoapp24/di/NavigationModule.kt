package ru.itis.demoapp24.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.demo24.navigation.Nav
import ru.itis.demo24.navigation.NavMain
import ru.itis.demoapp24.nav.NavImpl
import ru.itis.demoapp24.nav.NavMainImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavToImpl(impl: NavImpl): Nav

    @Binds
    @Singleton
    fun bindNavMainToImpl(impl: NavMainImpl): NavMain
}