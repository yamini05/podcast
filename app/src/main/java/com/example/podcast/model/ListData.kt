package com.example.podcast.model

data class ListData<T>(
    var data: ArrayList<T>? = null,
    var success: Boolean = false
)