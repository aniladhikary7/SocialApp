package com.anil.htf.sharePost

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anil.htf.R
import com.anil.htf.databinding.ActivitySharePostBinding
import com.anil.htf.utility.UtilConstants
import java.text.SimpleDateFormat
import java.util.*


class SharePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharePostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Create Post"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.creat_post_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.post_share -> {
                sharePost()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun sharePost(){
        val postText: String = binding.createPostEt.text.trim().toString()
        val userName: String = "Xyz Xyz"
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val currentDate = sdf.format(Date())

        if (binding.createPostEt.text.trim().isEmpty()){
            Toast.makeText(this, "Please write something!", Toast.LENGTH_LONG).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(UtilConstants.EXTRA_POST, postText)
        data.putExtra(UtilConstants.EXTRA_TIME, currentDate)
        data.putExtra(UtilConstants.EXTRA_USER, userName)
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}