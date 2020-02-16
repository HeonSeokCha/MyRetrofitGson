package com.example.myretrofitgson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.myretrofitgson.dataClass.singleUser
import com.example.myretrofitgson.retrofitService.RetrofitApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitApi::class.java)
        service.listUser()?.enqueue(object : Callback<singleUser> {
            override fun onFailure(call: Call<singleUser>?, t: Throwable?) {
                Log.d("Failed",t.toString())
            }
            override fun onResponse(call: Call<singleUser>, response: Response<singleUser>) {
                Glide.with(this@MainActivity).load(response.body()!!.data.avatar).into(imageView)
            }
        })
    }
}
