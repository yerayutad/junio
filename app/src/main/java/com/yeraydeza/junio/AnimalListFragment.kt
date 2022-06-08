package com.yeraydeza.junio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeraydeza.junio.adapter.AnimalAdapter
import com.yeraydeza.junio.apiService.APIService
import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.databinding.FragmentAnimalListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimalListFragment : Fragment() {

    private lateinit var adapter: AnimalAdapter
    private lateinit var binding:FragmentAnimalListBinding
    private val animals = ArrayList<AnimalDataItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAnimalListBinding.inflate(layoutInflater)
        initRecyclerView()
        getAll()
    }

    private fun initRecyclerView() {
        adapter = AnimalAdapter(animals)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animal_list, container, false)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://serveranimalutad-env.eba-zr9dsz3t.eu-west-3.elasticbeanstalk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAll(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAnimals()
            val pup = call.body()
            if (call.isSuccessful){
                animals.clear()
                animals.addAll(call.body())
            }else{

            }
            }

        }
    }

