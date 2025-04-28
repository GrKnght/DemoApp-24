package ru.itis.demo24.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.itis.demo24.data.remote.DogFactsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideDogApi(okHttpClient: OkHttpClient): DogFactsApi {
        val builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .build()

        return builder.create(DogFactsApi::class.java)
    }
}