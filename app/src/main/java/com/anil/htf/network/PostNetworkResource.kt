package com.anil.htf.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anil.htf.posts.models.PostApiResponse
import com.anil.htf.posts.models.PostResponse
import com.anil.htf.utility.Prefs
import com.anil.htf.utility.UtilConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class PostNetworkResource private constructor(
    private val htfApiService: HTFApiService, prefs: Prefs
) {

    companion object {
        @Volatile
        private var INSTANCE: PostNetworkResource? = null
        fun getInstance(
            htfApiService: HTFApiService,
            prefs: Prefs
        ) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PostNetworkResource(htfApiService, prefs).also { it }
            }
    }


    private var postResponseCall: Call<PostResponse>? = null
    private val _postsLiveData = MutableLiveData<PostApiResponse<PostResponse>>()
    val postLiveData: LiveData<PostApiResponse<PostResponse>>
        get() = _postsLiveData
    private val accessToken = prefs.accessToken
    private val tokenType = prefs.tokenType
    private val authentication = "$tokenType $accessToken"

    fun callRecipeListApi() {
        Timber.d("Calling...")
        Timber.d(authentication)
        postResponseCall = htfApiService.getPostList(
            UtilConstants.DEVICE_ID,
            UtilConstants.DEVICE_TYPE, UtilConstants.LOCATE, UtilConstants.ORDER_BY, authentication
        )
        val callback = object : Callback<PostResponse> {
            override fun onResponse(
                call: Call<PostResponse>,
                response: Response<PostResponse>
            ) {
                if (response.isSuccessful) {
                    Timber.d("Success Calling...")
                    val responseContent = response.body()
                    val status = UtilConstants.API_STATUS_SUCCESS
                    val apiResponse = PostApiResponse(status, responseContent)
                    _postsLiveData.postValue(apiResponse)
                } else {
                    failedResponse(UtilConstants.API_STATUS_FAILURE)
                    Timber.d("Success/Failure Calling...")
                    Timber.d(response.body()?.message)
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Timber.d(" Failure Calling...")
                if (call.isCanceled) {
                    failedResponse(UtilConstants.API_STATUS_CANCELLED)
                } else {
                    failedResponse(UtilConstants.API_STATUS_FAILURE)
                }
            }
        }
        postResponseCall?.enqueue(callback)

    }

    private fun failedResponse(status: String) {
        val apiResponse = PostApiResponse<PostResponse>(status)
        _postsLiveData.postValue(apiResponse)
    }
}