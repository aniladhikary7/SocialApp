package com.anil.htf.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anil.htf.posts.models.Posts
import com.anil.htf.repository.PostRepository

class PostViewModel internal constructor(private var repository: PostRepository) :
    ViewModel() {
    private fun callRecipeListApi() = repository.fetchAllPostFromApi()

    init {
        callRecipeListApi()
    }
    override fun onCleared() {
        super.onCleared()
    }

    fun insert(post: Posts) {
        repository.insert(post)
    }

    fun fetchRecipeListApiStatus() = repository.postResponseLiveData

    var recipeList: LiveData<List<Posts>> = repository.fetchAllPostFromDB()


}