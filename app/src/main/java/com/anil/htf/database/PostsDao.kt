package com.anil.htf.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anil.htf.posts.models.Posts

@Dao
interface PostsDao {

    @Insert
    fun insert(posts: Posts)

    @Insert
    fun postsInsert(posts: List<Posts>)

    @Delete
    fun postDelete(posts: Posts)

    @Update
    fun postUpdate(posts: Posts)

    @Query("DELETE FROM posts_table")
    fun deleteAllPosts()

    @Query("SELECT * FROM posts_table ORDER BY id DESC")
    fun getAllPosts(): LiveData<List<Posts>>
}