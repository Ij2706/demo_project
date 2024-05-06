package com.example.demoproject


import com.example.demoproject.Response.NewResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService){

    fun fetchData(): Single<NewResponse> {
        return apiService.fetchData("c91c6efaef744928b436d46240a59b13","tesla")
    }

}