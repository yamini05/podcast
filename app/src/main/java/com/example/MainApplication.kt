package com.podcast

import ErrorFromApi
import android.app.Application
import android.content.Context
import com.example.podcast.R
import com.example.podcast.Storage.PrefHelper
import com.example.podcast.interfaces.CustomErrorInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        preferHelper = PrefHelper(ctx)
    }
    companion object {
        lateinit var preferHelper: PrefHelper
        private lateinit var ctx: Context

        fun <T> doServerCall(
            method: suspend () -> Response<T>?,
            serverInterface: CustomErrorInterface<T>
        ): Job? {

            return CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = method.invoke()

                    if (response?.isSuccessful == true && response.body() != null) {
                        serverInterface.onSuccess(response.body()!!)
                    } else {
                        handleError(response, serverInterface)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    serverInterface.onError(e)
                }
            }
        }

        private fun <T> handleError(response: Response<T>?, customErrorInterface: CustomErrorInterface<T>) {
            val stringJson = response?.errorBody()?.string()

            customErrorInterface.onApiError(
                ErrorFromApi(
                    response?.code() ?: -1,
                    ctx.resources?.getString(R.string.error_general) ?: "",
                    stringJson
                )
            )

        }
    }


}