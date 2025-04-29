package ru.itis.demo24.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.demo24.data.repository.SearchRepositoryImpl
import ru.itis.demo24.data.repository.SongDataRepositoryImpl
import ru.itis.demo24.domain.repositories.SearchRepository
import ru.itis.demo24.domain.repositories.SongDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataBinderModule {

    @Binds
    @Singleton
    fun bindSongDataRepositoryToImplementation(impl: SongDataRepositoryImpl): SongDataRepository

    @Binds
    @Singleton
    fun bindSearchRepositoryToImplementation(impl: SearchRepositoryImpl): SearchRepository
}