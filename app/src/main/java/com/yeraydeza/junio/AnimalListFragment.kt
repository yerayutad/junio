package com.yeraydeza.junio

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeraydeza.junio.adapter.AnimalAdapter
import com.yeraydeza.junio.apiService.APIService
import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.databinding.FragmentAnimalListBinding
import com.yeraydeza.junio.model.AnimalsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimalListFragment : Fragment() {

    private lateinit var binding: FragmentAnimalListBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAnimalListBinding.inflate(layoutInflater)
        getAll()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animal_list, container, false)
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://serveranimalutad-env.eba-zr9dsz3t.eu-west-3.elasticbeanstalk.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private fun getAll() {
        CoroutineScope(Dispatchers.IO).launch {
            val api = retrofit.create(APIService::class.java)
            api.getAnimals().enqueue(object : Callback<List<AnimalDataItem>> {
                override fun onResponse(
                    call: Call<List<AnimalDataItem>>,
                    response: Response<List<AnimalDataItem>>
                ) {
                   d("d", "name ${response.body()!!}")
                    showData(response.body()!!)
                }

                override fun onFailure(call: Call<List<AnimalDataItem>>, t: Throwable) {
                    d("d", "f")
                }

            })

        }

    }

    private fun showData(animalDataItem: List<AnimalDataItem>) {
        binding.rvFragment.apply {
            binding.rvFragment.layoutManager = LinearLayoutManager(activity)
            binding.rvFragment.adapter = AnimalAdapter(animalDataItem)
        }
    }
}

