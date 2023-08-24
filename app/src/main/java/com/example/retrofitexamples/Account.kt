package com.example.retrofitexamples

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id") private val id: String,
    @SerializedName("password") private val password: String
)
