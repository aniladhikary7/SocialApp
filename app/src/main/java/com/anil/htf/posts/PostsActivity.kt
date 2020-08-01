package com.anil.htf.posts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anil.htf.R
import com.anil.htf.posts.models.Posts
import com.anil.htf.sharePost.SharePostActivity
import com.anil.htf.utility.InjectUtils
import com.anil.htf.utility.UtilConstants
import com.anil.htf.viewModel.PostViewModel

class PostsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var addStoryBtn: LinearLayout
    private val postViewModel: PostViewModel by viewModels {
        InjectUtils.providePostViewModelFactory(this)
    }
    private fun initialise(){
        recyclerView = findViewById(R.id.post_list_rv)
        addStoryBtn = findViewById(R.id.add_story_btn)
        addStoryBtn.setOnClickListener(this)
        postAdapter = PostAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = postAdapter

        postViewModel.recipeList.observe(this, Observer {
            postAdapter.submitPosts(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        initialise()
    }

    override fun onClick(v: View?) {
        if (v == addStoryBtn){
            val intent = Intent(this, SharePostActivity::class.java)
            startActivityForResult(intent, UtilConstants.SHARE_POST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UtilConstants.SHARE_POST && resultCode == Activity.RESULT_OK) {
            data?.apply {
                val postStatus = getStringExtra(UtilConstants.EXTRA_POST)
                val userName = getStringExtra(UtilConstants.EXTRA_USER)
                val currentTime = getStringExtra(UtilConstants.EXTRA_TIME)

                var post = Posts(userName, currentTime, postStatus)
                postViewModel.insert(post)
            }
            Toast.makeText(this, "Successfully uploaded!", Toast.LENGTH_LONG).show()
        }
    }
}