package com.anil.htf.posts.models

data class PostApiResponse<T>(val status: String, val response: T? = null)