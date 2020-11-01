package com.mylearning.unsplashphotoapp.di

import com.mylearning.unsplashphotoapp.api.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/** Object that we need across our application  : We will make this object available in our repository **/
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    /** Telling dagger how to create object of classes that we need **/

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit) : UnsplashApi =
        retrofit.create(UnsplashApi::class.java)


}