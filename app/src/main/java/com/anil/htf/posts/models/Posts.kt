package com.anil.htf.posts.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts_table")
data class Posts(
    @SerializedName("comments")
    val comments: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_gif")
    val isGif: Int?,
    @SerializedName("is_hashtag")
    val isHashtag: Int?,
    @SerializedName("is_image")
    val isImage: Int?,
    @SerializedName("is_shared")
    val isShared: Int?,
    @SerializedName("is_video")
    val isVideo: Int?,
    @SerializedName("is_youtube")
    val isYoutube: Int?,
    @SerializedName("like_data")
    val likeData: String?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("local_id")
    val localId: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("original_created_at")
    val originalCreatedAt: String?,
    @SerializedName("original_message")
    val originalMessage: String?,
    @SerializedName("original_posted_by")
    val originalPostedBy: String?,
    @SerializedName("original_posted_by_image")
    val originalPostedByImage: String?,
    @SerializedName("original_privacy")
    val originalPrivacy: String?,
    @SerializedName("original_user_id")
    val originalUserId: Int?,
    @SerializedName("posted_by")
    val postedBy: String?,
    @SerializedName("posted_by_image")
    val postedByImage: String?,
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("report_data")
    val reportData: String?,
    @SerializedName("shares")
    val shares: Int?,
    @SerializedName("user_id")
    val userId: Int?
){
    constructor(userName: String?, time: String?, postStatus: String?): this(
        0,
        time,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        0,
        null,
        postStatus,
        null,
        null,
        null,
        null,
        null,
        null,
        userName,
        null,
        null,
        null,
        0,
        null
    )
}