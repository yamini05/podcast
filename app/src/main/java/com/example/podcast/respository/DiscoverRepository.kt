package com.example.podcast.respository

import DiscoverPodRequest.getBannersList
import ErrorFromApi
import androidx.lifecycle.MutableLiveData
import com.example.podcast.interfaces.CustomErrorInterface
import com.example.podcast.model.BannerList
import com.example.podcast.model.ListData
import com.podcast.MainApplication
class DiscoverRepository {

    fun getBannerList(): MutableLiveData<ArrayList<BannerList>> {
        val mutableData = MutableLiveData<ArrayList<BannerList>>()

        MainApplication.doServerCall({ getBannersList() },
            object : CustomErrorInterface<ListData<BannerList>> {
                override fun onApiError(e: ErrorFromApi) {
                    mutableData.postValue(null)
                }
                override fun onError(e: Throwable) {
                    mutableData.postValue(null)
                }

                override fun onSuccess(data: ListData<BannerList>) {
                    mutableData.postValue(data.data)
                }
            })

        return mutableData
    }

    /* fun getPodCasts() : MutableLiveData<Resource<List<PodCast>>> {

         val mutableData = MutableLiveData<Resource<List<PodCast>>>()

         BaseApplication.doServerCall({NetworkRequests.getPodCasts()},
             object : ServerInterface<OnlyDataList<PodCast>> {

             override fun onCustomError(e: ApiError) {
                 mutableData.postValue(Resource.error(e.message, null))
             }

             override fun onError(e: Throwable) {
                 mutableData.postValue(Resource.error(Resource.GENERIC_ERROR, null))
             }

             override fun onSuccess(data: OnlyDataList<PodCast>) {
                 mutableData.postValue(Resource.success(data.data))
             }
         })

         return mutableData
     }*/
}