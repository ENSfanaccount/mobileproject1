package com.example.mobileproject1.examentercerparcial.network

import com.example.mobileproject1.examentercerparcial.model.Student
import retrofit2.Response
import retrofit2.http.GET

interface StudentApi {
    @GET("2b6648feaf70d1116e6f468b263d73c9/raw/50cf034eb87e273d6746cf2ffb9afb5a409f80b2/students.json")
    suspend fun getStudents(): Response<List<Student>>
}
