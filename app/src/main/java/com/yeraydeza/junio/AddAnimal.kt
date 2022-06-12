package com.yeraydeza.junio

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yeraydeza.junio.apiService.APIService
import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.data.Breed
import com.yeraydeza.junio.databinding.FragmentAddAnimalBinding
import com.yeraydeza.junio.databinding.FragmentAnimalListBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class AddAnimal : Fragment() {

    private lateinit var binding: FragmentAddAnimalBinding
    private lateinit var name: String
    private lateinit var age: String
    private lateinit var kind: String
    private lateinit var desc: String
    private lateinit var id: String
    private lateinit var image: String
    private lateinit var breedId: String
    private lateinit var breedDesc: String
    private lateinit var breedName: String
    private lateinit var breed: Breed
    private lateinit var animalObjeto: AnimalDataItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddAnimalBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            age = binding.etAge.text.toString()
            name = binding.etName.text.toString()
            kind = binding.etKind.text.toString()
            id = binding.etAnimal.text.toString()
            desc = binding.etDesc.text.toString()
            image = binding.etImagen.text.toString()
            breedId = binding.etIdBreed.text.toString()
            breedName = binding.etNameBreed.text.toString()
            breedDesc = binding.etDescBreed.text.toString()

            breed = Breed(breedDesc, breedId, breedName)
            d("add", "$id, $name, $kind, $breed, $desc, ${age.toInt()},$image ")
            animalObjeto = AnimalDataItem(age.toInt(), breed, desc, id, image, kind, name)
            postAnimal(animalObjeto)

        }
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://serveranimalutad-env.eba-zr9dsz3t.eu-west-3.elasticbeanstalk.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun postAnimal(animal1: AnimalDataItem) {
        val api = retrofit.create(APIService::class.java)
        api.postAnimals(animal1)
            .enqueue(object : Callback<AnimalDataItem> {
                override fun onResponse(
                    call: Call<AnimalDataItem>,
                    response: Response<AnimalDataItem>
                ) {
                    if (response.isSuccessful) {
                        d("add", "animal añadido")
                    } else {
                        d("add", "error en la conexion ${response}")
                    }
                }

                override fun onFailure(call: Call<AnimalDataItem>, t: Throwable) {
                    d("add", "error en la conexion", t)
                    Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
                }
            })
    }

}