package com.oliverworks.lyricsfinder.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oliverworks.lyricsfinder.util.NonUiConstants
import kotlinx.android.parcel.Parcelize

@Entity(tableName = NonUiConstants.MUSIC_DATA_TABLE)
@Parcelize
data class Music(
    @PrimaryKey(autoGenerate = true)
    //TODO is this needed actually?
    @ColumnInfo(name = "subscriber_name")
    var id : Int = 0,
    var artist:String = "",
    var name:String = "",
    var lyric:String = ""
): Parcelable