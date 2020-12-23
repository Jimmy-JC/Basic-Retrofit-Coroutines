package com.example.basicretrofitcoroutines
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_post.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        viewModels<PostVM>().value.post.observe(this, { post.text = it.body })
    }
}

data class Post(val id: Int, val title: String, val body: String)

interface ApiService { @GET("posts/1") suspend fun getPost(): Post }

class PostRepo { suspend fun getPost() = ServiceProvider.getAPI().getPost() }

class PostVM : ViewModel() { val post = liveData { emit(PostRepo().getPost()) } }

object ServiceProvider {
    fun getAPI(): ApiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}