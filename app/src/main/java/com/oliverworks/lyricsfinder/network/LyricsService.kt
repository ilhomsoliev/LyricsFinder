package com.oliverworks.lyricsfinder.network

import com.oliverworks.lyricsfinder.models.Lyrics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsService {

    @GET("/v1/{artist}/{artistSong}")
    suspend fun getLyrics(
        @Path(value = "artist") artist: String,
        @Path(value = "artistSong") artistSong: String
    ): Response<Lyrics>
}