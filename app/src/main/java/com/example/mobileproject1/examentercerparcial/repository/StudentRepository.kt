package com.example.mobileproject1.examentercerparcial.repository

import com.example.mobileproject1.examentercerparcial.network.StudentApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/ingjromo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: StudentApi by lazy {
        retrofit.create(StudentApi::class.java)
    }
}
