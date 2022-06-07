package com.yeraydeza.junio

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("/animals")
    suspend fun getAnimals(): Call<List<AnimalDataItem>>
}