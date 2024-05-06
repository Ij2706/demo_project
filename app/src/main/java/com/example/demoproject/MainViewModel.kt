package com.example.demoproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject.Response.NewResponse
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

     fun fetchData( callback : (Single<NewResponse>)->Unit ) {
        viewModelScope.launch {
            callback.invoke(dataRepository.fetchData())
        }
    }
}