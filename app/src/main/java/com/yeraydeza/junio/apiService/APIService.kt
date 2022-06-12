package com.yeraydeza.junio.apiService

import com.yeraydeza.junio.data.AnimalDataItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("animals")
    fun getAnimals(): Call<List<AnimalDataItem>>

    @POST("animals")
    fun postAnimals(@Body animalData: AnimalDataItem): Call<AnimalDataItem>

    @GET("animals/{idAnimal}")
    suspend fun getAnimalsById(@Path("idAnimal") id: String): Response<AnimalDataItem>
}

