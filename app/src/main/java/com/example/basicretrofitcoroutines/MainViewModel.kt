package com.example.basicretrofitcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class MainViewModel: ViewModel() {
    val post: LiveData<Post> = liveData {
        emit(MainRepository().getPost())
    }
}