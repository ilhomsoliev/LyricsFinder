package com.oliverworks.lyricsfinder.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.oliverworks.lyricsfinder.models.Music
import com.oliverworks.lyricsfinder.util.NonUiConstants

@Dao
interface LyricsDao{

    @Insert
    suspend fun insertMusic(music:Music)

    @Delete
    suspend fun deleteMusic(music:Music)

    @Query("SELECT * FROM ${NonUiConstants.MUSIC_DATA_TABLE}")
    suspend fun getFavoriteMusic():List<Music>

    @Query("DELETE FROM ${NonUiConstants.MUSIC_DATA_TABLE}")
    suspend fun deleteAllMusic():Int
}