package com.yeraydeza.junio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yeraydeza.junio.R
import com.yeraydeza.junio.data.AnimalData
import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.databinding.AnimalItemBinding

class AnimalAdapter(val dataset: ArrayList<AnimalDataItem>):RecyclerView.Adapter<AnimalViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AnimalViewHolder(layoutInflater.inflate(R.layout.animal_item, parent, false))
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.render(dataset[position])

    }

    override fun getItemCount(): Int = dataset.size
}

class AnimalViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val binding = AnimalItemBinding.bind(view)

    fun render(animal: AnimalDataItem){
        binding.tvAge.text = animal.name
        binding.tvAge.text = animal.age.toString()
        binding.tvKind.text = animal.kind
        binding.tvBreed.text = animal.breed.name
        bind(animal.imageUrl)

    }

    fun bind(image:String){
        Picasso.get().load(image).into(binding.imageView)
    }

}
