package com.farhan.suitmediatest.retrofit

import com.farhan.suitmediatest.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("/api/users")
    fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Call<UserResponse>
}