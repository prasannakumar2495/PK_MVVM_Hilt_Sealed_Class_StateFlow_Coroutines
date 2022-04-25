package com.example.pkmvvmhiltsealedclassstateflowcoroutines.di

import com.example.pkmvvmhiltsealedclassstateflowcoroutines.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @SomeClass
    @Singleton
    @Provides
    fun provideURL1() = "https://jsonplaceholder.typicode.com"

    @Singleton
    @Provides
    fun provideURL2() = "https://jsonplaceholder.typicode.com"

    @Singleton
    @Provides
    fun ProvideApiService(@SomeClass url: String): ApiService =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
}

/**
 * This is just for reference, this is a replacement for @Named annotation from Dagger2.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SomeClass