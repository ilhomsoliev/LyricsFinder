package com.oliverworks.lyricsfinder.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lyrics(
    @SerializedName("lyrics")
    val lyrics: String
):Parcelable