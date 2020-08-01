package com.anil.htf.startup.models


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("chat_notify")
    val chatNotify: String?,
    @SerializedName("comment_notify")
    val commentNotify: String?,
    @SerializedName("cover_image")
    val coverImage: Any?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("dial_code")
    val dialCode: String?,
    @SerializedName("dob")
    val dob: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("follow_notify")
    val followNotify: String?,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("following")
    val following: Int?,
    @SerializedName("friend_request_notify")
    val friendRequestNotify: String?,
    @SerializedName("friends")
    val friends: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("is_returner")
    val isReturner: Int?,
    @SerializedName("last_connected_at")
    val lastConnectedAt: Any?,
    @SerializedName("last_status")
    val lastStatus: Any?,
    @SerializedName("level")
    val level: Int?,
    @SerializedName("like_notify")
    val likeNotify: String?,
    @SerializedName("mobile")
    val mobile: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("post_notify")
    val postNotify: String?,
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("share_notify")
    val shareNotify: String?,
    @SerializedName("username")
    val username: String?
)