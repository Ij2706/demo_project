package com.example.demoproject

import com.example.demoproject.Response.NewResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET("everything")
    fun fetchData(
        @Query("apiKey") key : String,
        @Query("q") type: String
    ): Single<NewResponse>


}