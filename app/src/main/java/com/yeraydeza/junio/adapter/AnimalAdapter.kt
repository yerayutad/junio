package com.yeraydeza.junio.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yeraydeza.junio.data.AnimalDataItem
import com.yeraydeza.junio.databinding.AnimalItemBinding

class AnimalAdapter(
    private val dataset: List<AnimalDataItem>,
    private val listener: (AnimalDataItem) -> Unit
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: AnimalItemBinding = AnimalItemBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun getItemCount() = dataset.size


    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal2 = dataset[position]
        d("d", "name ${animal2.name}")
        holder.binding.tvName.text = animal2.name
        holder.binding.tvAge.text = animal2.age.toString()
        holder.binding.tvKind.text = animal2.kind
        holder.binding.tvBreed.text = animal2.breed.name
        holder.bind(animal2.imageUrl)
        holder.binding.root.setOnClickListener { listener(animal2) }

    }

    class AnimalViewHolder(val binding: AnimalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String?) {
            if (!image.isNullOrBlank())
                Picasso.get().load(image).into(binding.imageView)
        }
    }
}
