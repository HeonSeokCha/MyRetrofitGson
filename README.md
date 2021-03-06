# MyRetrofitGson
This is Simple MyRetrofit&amp;Gson Test.

* Use Library
  * Gson Converter : https://github.com/square/retrofit/tree/master/retrofit-converters/gson
  * Retrofit2 : https://square.github.io/retrofit/
  * Glide : https://github.com/bumptech/glide
  * JSON to Kotlin Class : https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
  
 
 * Target JSON : https://reqres.in/api/users/2
  *
      "data":{
      
      "id":2,
      
      "email":"janet.weaver@reqres.in",
      
      "first_name":"Janet",
      
      "last_name":"Weaver",
      
      "avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"
    }
    
    
해당 JSON 형식 데이터를 GSON Converter로 변환하여 dataClass 형식으로 받아 response의 Body에서 이미지 경로를 가져와 Glide로 적용시킵니다.

* Example Code

```
@GET("users/2")
fun listUser() : Call<singleUser>
```

MainActivity.kt

```
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
```

![test](https://user-images.githubusercontent.com/55890012/74604927-350e3a80-5106-11ea-9a78-110ff190da75.PNG)

* send POST JSONObject

```
@POST("login")
fun loginUser(@Body req:RequestBody) : Call<sendloginInfo>
```

MainActivity.kt

```
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
```

![제목 없음](https://user-images.githubusercontent.com/55890012/74674404-9d3b4a00-51f4-11ea-8397-85c3f3f1bd2a.png)
