package com.yeraydeza.junio.apiService

import com.yeraydeza.junio.data.AnimalDataItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("/animals")
    fun getAnimals(): Response<AnimalDataItem>
}