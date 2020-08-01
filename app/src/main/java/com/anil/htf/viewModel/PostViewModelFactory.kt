package com.anil.htf.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anil.htf.repository.PostRepository

class PostViewModelFactory(private var postRepository: PostRepository):
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        PostViewModel(postRepository) as T
}