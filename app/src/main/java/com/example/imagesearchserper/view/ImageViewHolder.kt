package com.example.imagesearchserper.view

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imagesearchserper.FullScreenImageActivity
import com.example.imagesearchserper.R
import com.example.imagesearchserper.databinding.ImageItemBinding
import com.example.imagesearchserper.model.Image

class ImageViewHolder(private val binding: ImageItemBinding, private val images: List<Image>) : RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            val intent = Intent(it.context, FullScreenImageActivity::class.java)
            intent.putExtra("images", images as ArrayList<Image>)
            intent.putExtra("position", adapterPosition)
            it.context.startActivity(intent)
        }
    }

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