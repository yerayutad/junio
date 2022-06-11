package com.yeraydeza.junio

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private lateinit var binding: FragmentAnimalListBinding
    private lateinit var animales : List<AnimalDataItem>
    private lateinit var  rv : RecyclerView
    private lateinit var animalID : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalListBinding.inflate(inflater, container, false)
        rv = binding.rvFragment
       /* binding.rvFragment.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )*/
        val btn = binding.button
        btn.setOnClickListener{
            d("btn","pulsado")
            val fragment = AddAnimal()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_host_fragment,fragment)?.commit()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAll()
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
                   animales = response.body()!!
                    showData(animales)
                    d("d", "animales ${animales}")
                    d("d", "animales ${response.body()!![0].name}")
                }

                override fun onFailure(call: Call<List<AnimalDataItem>>, t: Throwable) {
                    d("d", "f")
                }

            })

        }

    }

    private fun showData(animalDataItem: List<AnimalDataItem>) {
        rv.apply {
            rv.layoutManager = GridLayoutManager(context, 2)
            rv.adapter?.notifyDataSetChanged()
            rv.adapter = AnimalAdapter(animalDataItem) { animal ->
                d("MainActivity", "animal pulsado: ${animal.id}")
                animalID = animal.id
                val fragment = AnimalDetailFragment()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment,fragment)?.commit()}
        }
    }
}

