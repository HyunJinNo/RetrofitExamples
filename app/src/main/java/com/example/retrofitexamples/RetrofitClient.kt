package com.example.retrofitexamples

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    /**
     * IP 주소: http://???.???.??.???:????/
     */
    private const val BASE_URL = "http://???.???.??.???:????"

    private fun getInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getApiService(): RetrofitInterface = getInstance().create(RetrofitInterface::class.java)
}