package com.example.retrofitexamples

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("sign-in/id={userId}&password={password}")
    fun signIn(@Path("userId") userId: String, @Path("password") password: String): Call<String>

    @POST("sign-up")
    suspend fun signUp(@Body account: Account): Response<AccountDataModel>
}