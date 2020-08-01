package com.anil.htf.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anil.htf.database.PostsDao
import com.anil.htf.network.PostNetworkResource
import com.anil.htf.posts.models.Posts
import com.anil.htf.utility.AppExecutors
import com.anil.htf.utility.SingleLiveEvent
import com.anil.htf.utility.UtilConstants

class PostRepository private constructor(
    private val postsDao: PostsDao,
    private val postNetworkResource: PostNetworkResource,
    private val appExecutors: AppExecutors
) {

    companion object{
        @Volatile private var instance: PostRepository? = null
        fun getInstance(
            postsDao: PostsDao,
            postNetworkResource: PostNetworkResource,
            appExecutors: AppExecutors
        ) =
            instance?: synchronized(this){
                instance?: PostRepository(postsDao,
                    postNetworkResource,
                    appExecutors)
                    .also { it }
            }
    }

    private val _postResponseLiveData = MutableLiveData<SingleLiveEvent<PostStatusEnum>>()
    val postResponseLiveData: LiveData<SingleLiveEvent<PostStatusEnum>>
        get() = _postResponseLiveData

    init {
        val postData =
            postNetworkResource.postLiveData
        postData.observeForever {
            appExecutors.diskIO().execute {
                when(it.status){
                    UtilConstants.API_STATUS_SUCCESS ->{
                        postsDao.deleteAllPosts()
                        postsDao.postsInsert(it.response?.data!!)
                        _postResponseLiveData.postValue(SingleLiveEvent(PostStatusEnum.SUCCESS))
                    }
                    UtilConstants.API_STATUS_CANCELLED -> {
                    }
                    else -> {
                        _postResponseLiveData.postValue(SingleLiveEvent(PostStatusEnum.FAILURE))
                    }
                }
            }
        }
    }

    fun fetchAllPostFromDB() = postsDao.getAllPosts()

    fun fetchAllPostFromApi() = postNetworkResource.callRecipeListApi()

    fun insert(post: Posts){
        appExecutors.diskIO().execute {
            postsDao.insert(post)
        }
    }
}