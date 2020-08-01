package com.anil.htf.startup.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val data: Data?,
    @SerializedName("message")
    val message: String?
)