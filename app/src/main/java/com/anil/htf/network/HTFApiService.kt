package com.anil.htf.network

import com.anil.htf.posts.models.PostResponse
import com.anil.htf.startup.models.LoginResponse
import com.anil.htf.utility.UtilConstants
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import timber.log.Timber
import java.lang.Error
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

interface HTFApiService {

    companion object Factory {
        fun create(): HTFApiService {

            val cookieManager = CookieManager()
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

            val httpLoggingInterceptor = HttpLoggingInterceptor(object :
                HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Timber.tag("OkHttp").d(message)
                }
            })

            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(80, TimeUnit.SECONDS)
                .connectTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .cookieJar(JavaNetCookieJar(cookieManager))
                .protocols(arrayListOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(UtilConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            Timber.e("Error")

            return retrofit.create(HTFApiService::class.java)
        }
    }

    @Headers("X-Requested-With: XMLHttpRequest")
    @FormUrlEncoded
    @POST("api/v1/login")
    fun doLogIn(
        @Field("device_id") deviceId: String,
        @Field("device_type") deviceType: String,
        @Field("locale") locate: String,
        @Field("is_otp_login") isOtpLogin: String,
        @Field("is_mobile") isMobile: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("fcm_id") fcmId: String
    ): Call<LoginResponse>

    @Headers("X-Requested-With:XMLHttpRequest",
        "Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("api/v1/posts")
    fun getPostList(
        @Field("device_id") deviceId: String,
        @Field("device_type") deviceType: String,
        @Field("locale") locate: String,
        @Field("order_by") orderBy: String,
        @Header("Authorization") auth: String
    ): Call<PostResponse>
}