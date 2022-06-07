package com.yeraydeza.junio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeraydeza.junio.databinding.AnimalItemBinding

class AnimalAdapter(private val dataset: List<AnimalDataItem>):RecyclerView.Adapter<AnimalViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: AnimalItemBinding = AnimalItemBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.tvName.text = item.name
        holder.binding.tvBreed.text = item.breed.name
        holder.binding.tvKind.text = item.kind.toString()
        holder.binding.tvAge.text = item.age.toString()
        holder.bind(item.imageUrl)

    }

    override fun getItemCount(): Int = dataset.size
}
