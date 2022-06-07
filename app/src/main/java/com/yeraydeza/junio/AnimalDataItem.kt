package com.yeraydeza.junio


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AnimalDataItem(
    @SerializedName("age")
    val age: Int,
    @SerializedName("breed")
    val breed: Breed,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("name")
    val name: String
)