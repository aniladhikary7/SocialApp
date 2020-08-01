package com.anil.htf.startup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.anil.htf.posts.PostsActivity
import com.anil.htf.R
import com.anil.htf.databinding.ActivitySplashBinding
import com.anil.htf.network.HTFApiService
import com.anil.htf.startup.models.LoginResponse
import com.anil.htf.utility.Prefs
import com.anil.htf.utility.UtilConstants
import retrofit2.Call
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        prefs = Prefs(this)
        getAccessToken()
    }

    private fun getAccessToken() {
        val apiService = HTFApiService.create()
        apiService.doLogIn(
            UtilConstants.DEVICE_ID,
            UtilConstants.DEVICE_TYPE,
            UtilConstants.LOCATE,
            UtilConstants.IS_OTP_LOGIN,
            UtilConstants.IS_MOBILE,
            UtilConstants.USERNAME,
            UtilConstants.PASSWORD,
            UtilConstants.FCM_ID
        ).enqueue(object : retrofit2.Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                setUpExit(resources.getString(R.string.conn_error))
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.body() != null && response.isSuccessful) {
                    val accessToken = response?.body()?.data?.accessToken
                    val tokenType = response?.body()?.data?.tokenType
                    openApp(accessToken,tokenType)
                    prefs.accessToken = accessToken
                    prefs.tokenType = tokenType

                }else{
                    binding.progressBar.visibility = View.GONE
                    setUpExit(response.body()?.message)
                }
            }

        })
    }

    private fun openApp(accessToken: String?, tokenType: String?){
        var intent = Intent(this, PostsActivity::class.java)
        intent.putExtra(UtilConstants.ACCESS_TOKEN, accessToken)
        intent.putExtra(UtilConstants.TOKEN_TYPE, tokenType)
        startActivity(intent)
        finish()
    }

    private fun setUpExit(message: String?){
        binding.initialTv.visibility = View.VISIBLE
        binding.exitBtn.visibility = View.VISIBLE
        binding.initialTv.text = message
    }
}