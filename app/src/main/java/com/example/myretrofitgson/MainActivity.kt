package com.example.myretrofitgson

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myretrofitgson.dataClass.sendloginInfo
import com.example.myretrofitgson.dataClass.singleUser
import com.example.myretrofitgson.retrofitService.RetrofitApi
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }
    private fun init(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitApi::class.java)

        service.listUser().enqueue(object : Callback<singleUser> {
            override fun onFailure(call: Call<singleUser>?, t: Throwable?) {
                Log.d("Failed",t.toString())
            }
            override fun onResponse(call: Call<singleUser>, response: Response<singleUser>) {
                Glide.with(this@MainActivity).load(response.body()!!.data.avatar).into(imageView)
            }
        })

        val jsonObjt = JSONObject()
        jsonObjt.put("email", "eve.holt@reqres.in")
        jsonObjt.put("password","cityslicka")
        Log.d("JSON : ",jsonObjt.toString())
        val req = RequestBody.create(MediaType.parse("application/json"),jsonObjt.toString())
        service.loginUser(req).enqueue(object  : Callback<sendloginInfo>{
            override fun onFailure(call: Call<sendloginInfo>, t: Throwable) {
                Log.d("Failed",t.toString())
            }

            override fun onResponse(call: Call<sendloginInfo>, response: Response<sendloginInfo>) {
                Log.d("Successful",response.isSuccessful.toString())
                textView.text = "Login Successful Here is Your Token : "+response.body()!!.token
            }
        })
    }
}
