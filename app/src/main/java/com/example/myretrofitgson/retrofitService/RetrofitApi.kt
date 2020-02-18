package com.example.myretrofitgson.retrofitService

import com.example.myretrofitgson.dataClass.sendloginInfo
import com.example.myretrofitgson.dataClass.singleUser
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @GET("users/2")
    fun listUser() : Call<singleUser>

    @POST("login")
    fun loginUser(@Body req:RequestBody) : Call<sendloginInfo>
}