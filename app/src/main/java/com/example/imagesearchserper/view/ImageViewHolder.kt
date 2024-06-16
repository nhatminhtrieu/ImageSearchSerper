package com.example.imagesearchserper.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearchserper.databinding.ImageItemBinding
import com.example.imagesearchserper.model.Image

class ImageViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        binding.image = image
        Glide.with(binding.imageView.context)
            .load(image.link)
            .into(binding.imageView)
    }
}