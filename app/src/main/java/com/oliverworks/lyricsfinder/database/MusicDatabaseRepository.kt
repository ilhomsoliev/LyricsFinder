package com.oliverworks.lyricsfinder.database

import com.oliverworks.lyricsfinder.models.Music
import javax.inject.Inject


class MusicDatabaseRepository @Inject constructor(private val lyricsDao: LyricsDao) {
    suspend fun insertMusic(music: Music) = lyricsDao.insertMusic(music)
    suspend fun deleteMusic(music: Music) = lyricsDao.deleteMusic(music)
    suspend fun getAllMusics() = lyricsDao.getFavoriteMusic()
    suspend fun deleteAllMusics() = lyricsDao.deleteAllMusic()
}