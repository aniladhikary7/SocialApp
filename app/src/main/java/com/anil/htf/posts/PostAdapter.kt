package com.anil.htf.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anil.htf.R
import com.anil.htf.posts.models.Posts
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostHolder>() {

    inner class PostHolder : RecyclerView.ViewHolder {

        var profileIv: CircleImageView
        var profileNameTv: TextView
        var postTimeTv: TextView
        var postIv: ImageView
        var postStatusTv: TextView
        var postLikesTv: TextView
        var postCommentsTv: TextView
        var postSharesTv: TextView

        constructor(view: View) : super(view) {
            profileIv = view.findViewById(R.id.post_profile_iv)
            profileNameTv = view.findViewById(R.id.profile_name_tv)
            postTimeTv = view.findViewById(R.id.post_time_tv)
            postIv = view.findViewById(R.id.post_iv)
            postStatusTv = view.findViewById(R.id.post_status_tv)
            postLikesTv = view.findViewById(R.id.post_likes_tv)
            postCommentsTv = view.findViewById(R.id.post_comments_tv)
            postSharesTv = view.findViewById(R.id.post_shares_tv)
        }
    }

    private var posts: List<Posts> = ArrayList<Posts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_item, parent, false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        posts[position]?.let { currentPost ->
            with(holder) {
                Picasso.get()
                    .load(R.drawable.profile_image)
                    .placeholder(R.drawable.profile_image)
                    .error(R.drawable.profile_image)
                    .into(profileIv)
                Picasso.get()
                    .load(R.drawable.post_image)
                    .placeholder(R.drawable.post_image)
                    .error(R.drawable.post_image)
                    .into(postIv)
                profileNameTv.text = currentPost.postedBy
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("dd MMM yyyy HH:mm aa")
                val output: String = formatter.format(parser.parse(currentPost.createdAt))
                postTimeTv.text = output
                postStatusTv.text = currentPost.message

                when {
                    currentPost.likes!! > 1 -> {
                        postLikesTv.text = currentPost.likes.toString() + " Likes"
                    }
                    currentPost.likes == 1 -> {
                        postLikesTv.text = currentPost.likes.toString() + " Like"
                    }
                    else -> {
                        postLikesTv.text = "Like"
                    }
                }

                when {
                    currentPost.shares!! > 1 -> {
                        postCommentsTv.text = currentPost.shares.toString() + " Comments"
                    }
                    currentPost.shares == 1 -> {
                        postCommentsTv.text = currentPost.shares.toString() + " Comment"
                    }
                    else -> {
                        postCommentsTv.text = "Comment"
                    }
                }

                when {
                    currentPost.shares!! > 1 -> {
                        postSharesTv.text = currentPost.shares.toString() + " Shares"
                    }
                    currentPost.shares == 1 -> {
                        postSharesTv.text = currentPost.shares.toString() + " Share"
                    }
                    else -> {
                        postSharesTv.text = "Share"
                    }
                }
            }
        }
    }

    fun submitPosts(posts: List<Posts>) {
        this.posts = posts
        notifyDataSetChanged()
    }
}