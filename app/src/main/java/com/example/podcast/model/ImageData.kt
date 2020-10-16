package com.example.podcast.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class ImageData(
    val description: String="",
    val id: Int=0,
    var subscriber: Int=0,
    val title: String="",
    val url: String=""
) : Parcelable