package com.example.imagesearchserper.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imagesearchserper.R
import com.example.imagesearchserper.databinding.ImageItemBinding
import com.example.imagesearchserper.model.Image

class ImageViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        binding.image = image
        Glide.with(binding.imageView.context)
            .load(image.imageUrl)
            .apply(RequestOptions().override(600, 600).centerCrop())
            .error(
                Glide.with(binding.imageView.context)
                    .load(R.drawable.baseline_error_24)
                    .apply(RequestOptions().override(600, 600).centerCrop())
            )
            .into(binding.imageView)
    }
}