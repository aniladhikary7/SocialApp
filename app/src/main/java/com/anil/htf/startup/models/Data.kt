package com.anil.htf.startup.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("data")
    val dataX: DataX?,
    @SerializedName("expires_in")
    val expiresIn: Int?,
    @SerializedName("expires_unit")
    val expiresUnit: String?,
    @SerializedName("token_type")
    val tokenType: String?
)