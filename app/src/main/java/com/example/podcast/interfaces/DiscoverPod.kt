package com.example.podcast.interfaces

import com.example.podcast.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DiscoverPod {
    @GET("getbannerlist")
    suspend fun getBannersList(): Response<ListData<BannerList>>

    @GET("getCommentsby/{id}")
    suspend fun getComments(@Path("id") bannerId : String): Response<ListData<Comment>>

    @GET("getallepisodesbyid/{id}")
    suspend fun getEpisodes(@Path("id") bannerId : String): Response<ListData<DataList>>

}