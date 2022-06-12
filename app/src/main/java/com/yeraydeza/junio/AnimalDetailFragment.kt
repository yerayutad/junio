package com.yeraydeza.junio

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.squareup.picasso.Picasso
import com.yeraydeza.junio.apiService.APIService
import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.databinding.FragmentAnimalDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AnimalDetailFragment : Fragment() {

    private lateinit var binding: FragmentAnimalDetailBinding
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ID).toString()
        }
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://serveranimalutad-env.eba-zr9dsz3t.eu-west-3.elasticbeanstalk.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        d("detail", id)
        getAnimalsById(id)
    }

    private fun getAnimalsById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val api = retrofit.create(APIService::class.java).getAnimalsById("$id")
            val api2 = api.body()
            activity?.runOnUiThread {
                if (api.isSuccessful) {
                    binding.tvNameD.text = api2?.name
                    binding.tvAgeD.text = api2?.age.toString()
                    binding.tvBreedD.text = api2?.breed?.name
                    binding.tvDescD.text = api2?.description
                    binding.tvKindD.text = api2?.kind
                    if (!api2?.imageUrl.isNullOrBlank())
                        Picasso.get().load(api2?.imageUrl).into(binding.imageView3)
                    if (api2 != null) {
                        d("detail", "${api2.id}${api2.name}${api2.age}${api2.breed}${api2.imageUrl}${api2.description}${api2.kind}")
                    }
                }
            }
        }
    }

    companion object {
        const val ID = "idAnimal"
    }
}