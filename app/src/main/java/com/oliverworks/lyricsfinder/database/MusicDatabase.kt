package com.oliverworks.lyricsfinder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oliverworks.lyricsfinder.models.Music

@Database(entities = [Music::class],version = 1)
abstract class MusicDatabase:RoomDatabase(){
    abstract val lyricsDao:LyricsDao
}