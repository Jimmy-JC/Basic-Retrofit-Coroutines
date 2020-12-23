package com.example.basicretrofitcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.post.observe(this, { result.text = it.title })
    }
}

class MainViewModel: ViewModel() {
    val post = liveData { emit(MainRepository().getPost()) }
}

class MainRepository {
    suspend fun getPost() = ServiceProvider.getAPI().getPost()
}

object ServiceProvider {
    fun getAPI(): ApiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}

interface ApiService {
    @GET("posts/1") suspend fun getPost(): Post
}

data class Post(val title: String, val body: String)




