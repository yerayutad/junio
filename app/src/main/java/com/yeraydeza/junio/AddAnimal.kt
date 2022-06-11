package com.yeraydeza.junio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yeraydeza.junio.apiService.APIService
import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.databinding.FragmentAddAnimalBinding
import com.yeraydeza.junio.databinding.FragmentAnimalListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddAnimal : Fragment() {

    private lateinit var binding: FragmentAddAnimalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAnimalBinding.inflate(inflater, container, false)
        val btn = binding.button2
        btn.setOnClickListener{
            Log.d("btn", "pulsado")
            val fragment = AnimalListFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_host_fragment,fragment)?.commit()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://serveranimalutad-env.eba-zr9dsz3t.eu-west-3.elasticbeanstalk.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun postAnimal(animal: AnimalDataItem) {
        val api = retrofit.create(APIService::class.java)
        api.postAnimals(animal).enqueue(object : Callback<AnimalDataItem>{
                override fun onResponse(call: Call<AnimalDataItem>, response: Response<AnimalDataItem>) {
                    if (response.isSuccessful) {
                    } else {
                        Log.e("Network", "error en la conexion")
                    }
                }

                override fun onFailure(call: Call<AnimalDataItem>, t: Throwable) {
                    Log.e("Network", "error en la conexion", t)
                    Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
                }
            })
    }

}