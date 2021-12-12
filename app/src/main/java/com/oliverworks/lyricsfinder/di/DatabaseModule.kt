package com.oliverworks.lyricsfinder.di

import android.content.Context
import androidx.room.Room
import com.oliverworks.lyricsfinder.database.LyricsDao
import com.oliverworks.lyricsfinder.database.MusicDatabase
import com.oliverworks.lyricsfinder.network.LyricsService
import com.oliverworks.lyricsfinder.util.NonUiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideLyricsDao(database: MusicDatabase):LyricsDao = database.lyricsDao
    @Provides
    fun provideLyricsDatabase(
        @ApplicationContext context: Context
    ): MusicDatabase =
        Room.databaseBuilder(context, MusicDatabase::class.java, NonUiConstants.MUSIC_DATA_DATABASE)
            .build()
}