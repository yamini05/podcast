package com.example.podcast.interfaces

import ErrorFromApi


interface CustomErrorInterface<T> {
    fun onApiError(e: ErrorFromApi)

    fun onError(e: Throwable)

    fun onSuccess(data: T )

}