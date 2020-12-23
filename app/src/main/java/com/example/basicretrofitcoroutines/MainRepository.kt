package com.example.basicretrofitcoroutines

class MainRepository {
    suspend fun getPost() =
        ServiceProvider.getAPI().getPost()
}