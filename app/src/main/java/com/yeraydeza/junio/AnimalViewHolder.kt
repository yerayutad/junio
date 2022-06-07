package com.yeraydeza.junio

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yeraydeza.junio.databinding.AnimalItemBinding

class AnimalViewHolder(val binding: AnimalItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(image:String){
        Picasso.get().load(image).into(binding.imageView)
    }
}