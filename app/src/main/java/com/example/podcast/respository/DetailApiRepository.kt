package com.example.podcast.respository

import ErrorFromApi
import androidx.lifecycle.MutableLiveData
import com.example.podcast.interfaces.CustomErrorInterface
import com.example.podcast.model.Comment
import com.example.podcast.model.DataList
import com.example.podcast.model.ListData
import com.podcast.MainApplication

class DetailApiRepository {

    fun getComments(bannerId: String) : MutableLiveData<ArrayList<Comment>> {

        val mutableData = MutableLiveData<ArrayList<Comment>>()

        MainApplication.doServerCall({DiscoverPodRequest.getComments(bannerId)},
            object : CustomErrorInterface<ListData<Comment>> {

                override fun onApiError(e: ErrorFromApi) {
                    mutableData.postValue(null)
                }

                override fun onError(e: Throwable) {
                    mutableData.postValue(null)
                }

                override fun onSuccess(data: ListData<Comment>) {
                    mutableData.postValue(data.data)
                }
            })

        return mutableData
    }

    fun getEpisodes(bannerId: String) : MutableLiveData<ArrayList<DataList>> {

        val mutableData = MutableLiveData<ArrayList<DataList>>()

        MainApplication.doServerCall({DiscoverPodRequest.getEpisodes(bannerId)},
            object : CustomErrorInterface<ListData<DataList>> {

                override fun onApiError(e: ErrorFromApi) {

                    mutableData.postValue(null)
                }

                override fun onError(e: Throwable) {
                    mutableData.postValue(null)
                }

                override fun onSuccess(data: ListData<DataList>) {
                    mutableData.postValue(data.data)
                }
            })

        return mutableData
    }
}