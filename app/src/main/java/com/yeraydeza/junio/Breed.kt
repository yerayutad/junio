package com.yeraydeza.junio


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)