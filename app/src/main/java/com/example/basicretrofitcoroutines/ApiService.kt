package com.example.basicretrofitcoroutines

import retrofit2.http.GET

interface ApiService {
    @GET("posts/1")
    suspend fun getPost(): Post
}