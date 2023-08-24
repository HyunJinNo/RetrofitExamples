package com.example.retrofitexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.retrofitexamples.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val retrofitService = RetrofitClient.getApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitService.signIn("MyId", "MyPassword").enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("RetrofitExamples", response.message())
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("RetrofitExamples", t.message ?: "")
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private suspend fun signUp(id: String, password: String): String {
        lateinit var result: String
        val account = Account(id, password)

        withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {
            try {
                val response = retrofitService.signUp(account)

                if (response.isSuccessful) {
                    result = response.body()?.result!!
                } else {
                    // 통신이 실패한 경우
                    result = ""
                    Log.e("RetrofitExamples", response.message())
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                result = ""
                Log.e("RetrofitExamples", e.message ?: "")
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(application, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return result
    }
}