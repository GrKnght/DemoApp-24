package ru.itis.demo24.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.demo24.data.repository.DogFactsRepositoryImpl
import ru.itis.demo24.domain.repositories.DogsFactsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataBinderModule {

    @Binds
    @Singleton
    fun bindDogRepository_toImpl(impl: DogFactsRepositoryImpl): DogsFactsRepository
}