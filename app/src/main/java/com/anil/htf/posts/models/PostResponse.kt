package com.anil.htf.posts.models


import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("data")
    val data: List<Posts>?,
    @SerializedName("message")
    val message: String?
)