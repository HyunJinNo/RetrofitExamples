package com.example.retrofitexamples

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("sign-in")
    fun signIn(@Query("userId") userId: String, @Query("password") password: String): Call<String>

    @POST("sign-up")
    suspend fun signUp(@Body account: Account): Response<AccountDataModel>
}