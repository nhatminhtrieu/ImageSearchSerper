package com.example.imagesearchserper.view

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imagesearchserper.FullScreenImageActivity
import com.example.imagesearchserper.databinding.ImageItemBinding
import com.example.imagesearchserper.model.Image

class ImageViewHolder(private val binding: ImageItemBinding, private var images: List<Image>) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            val intent = Intent(it.context, FullScreenImageActivity::class.java)
            intent.putExtra("imagesList", images as ArrayList<Image>)
            intent.putExtra("position", getBindingAdapterPosition())

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (it.context as Activity),
                androidx.core.util.Pair.create(binding.imageView, ViewCompat.getTransitionName(binding.imageView)!!)            )

            it.context.startActivity(intent, options.toBundle())
        }
    }

    fun bind(image: Image) {
        ViewCompat.setTransitionName(binding.imageView, image.imageUrl)
        binding.image = image
        val width = if (image.thumbnailWidth < 600) 600 else image.thumbnailWidth
        val height = if (image.thumbnailHeight < 600) 600 else image.thumbnailHeight
        Glide.with(binding.imageView.context)
            .load(image.imageUrl)
            .apply(RequestOptions().override(width, height))
            .placeholder(CircularProgressDrawable(binding.imageView.context))
            .into(binding.imageView)
    }

    fun updateImages(newImages: List<Image>) {
        images = newImages
        Log.d("MainActivityABC", "Images updated")
    }
}