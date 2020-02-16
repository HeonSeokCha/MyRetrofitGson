package com.example.myretrofitgson.retrofitService

import com.example.myretrofitgson.dataClass.singleUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitApi {
    @Headers("accept: application/json",
        "content-type: application/json")
    @GET("users/2")
    fun listUser() : Call<singleUser>
}