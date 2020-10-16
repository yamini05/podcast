package com.example.podcast.viewmodel

import androidx.lifecycle.ViewModel
import com.example.podcast.respository.DiscoverRepository

class DiscoverViewModel : ViewModel() {

    private val repository = DiscoverRepository()

    fun getBannerList() = repository.getBannerList()


}