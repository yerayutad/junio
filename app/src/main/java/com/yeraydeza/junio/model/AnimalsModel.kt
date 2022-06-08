package com.yeraydeza.junio.model

import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.data.Breed

class AnimalsModel(
    val age: Int,
    val breed: Breed,
    val description: String,
    val id: String,
    val imageUrl: String,
    val kind: String,
    val name: String
)

fun AnimalDataItem.toAnimals(): AnimalsModel = AnimalsModel(
    this.age,
    this.breed,
    this.description,
    this.id,
    this.imageUrl,
    this.kind,
    this.name
)

fun List<AnimalDataItem>.toAnimals(): List<AnimalsModel> =
    this.map { AnimalDataItem -> AnimalDataItem.toAnimals() }