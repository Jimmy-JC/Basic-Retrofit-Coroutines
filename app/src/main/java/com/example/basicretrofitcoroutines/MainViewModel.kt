package com.example.basicretrofitcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class MainViewModel: ViewModel() {
    val post = liveData { emit(MainRepository().getPost()) }
}