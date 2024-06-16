package com.example.imagesearchserper.view

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imagesearchserper.R
import com.example.imagesearchserper.databinding.ImageItemBinding
import com.example.imagesearchserper.model.Image

class ImageViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        binding.image = image
        val width = if (image.thumbnailWidth < 600) 600 else image.thumbnailWidth
        val height = if (image.thumbnailHeight < 600) 600 else image.thumbnailHeight
        Glide.with(binding.imageView.context)
            .load(image.imageUrl)
            .apply(RequestOptions().override(width, height).centerCrop())
            .placeholder(CircularProgressDrawable(binding.imageView.context))
            .into(binding.imageView)
    }
}