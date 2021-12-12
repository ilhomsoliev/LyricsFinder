package com.oliverworks.lyricsfinder.di

import com.google.gson.GsonBuilder
import com.oliverworks.lyricsfinder.network.LyricsService
import com.oliverworks.lyricsfinder.util.NonUiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideService(): LyricsService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(NonUiConstants.BASE_URL)
        .build()
        .create(LyricsService::class.java)
}