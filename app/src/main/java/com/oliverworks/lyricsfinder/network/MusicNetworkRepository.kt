package com.oliverworks.lyricsfinder.network

import com.oliverworks.lyricsfinder.models.Lyrics
import com.oliverworks.lyricsfinder.util.Resource
import javax.inject.Inject


class MusicNetworkRepository @Inject constructor(private val service: LyricsService) {

    suspend fun getLyrics(artistName: String, songName: String): Resource<Lyrics> =
        try {
            val response = service.getLyrics(artistName, songName)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Could not connected.", null)
        }
}