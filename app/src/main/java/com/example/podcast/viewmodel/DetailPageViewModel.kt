package com.example.podcast.viewmodel

import androidx.lifecycle.ViewModel
import com.example.podcast.respository.DetailApiRepository

class DetailPageViewModel: ViewModel() {

    private val repository = DetailApiRepository()

    fun getComments(bannerId: String) = repository.getComments(bannerId)

    fun getEpisodes(bannerId: String) = repository.getEpisodes(bannerId)


}