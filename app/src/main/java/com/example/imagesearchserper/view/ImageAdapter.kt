package com.example.imagesearchserper.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imagesearchserper.R
import com.example.imagesearchserper.model.Image

class ImageAdapter(private var images: List<Image>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        Glide.with(holder.imageView.context)
            .load(image.imageUrl)
            .apply(RequestOptions().override(600, 600).centerCrop())
            .error(
                Glide.with(holder.imageView.context)
                    .load(R.drawable.baseline_error_24)
                    .apply(RequestOptions().override(600, 600).centerCrop())
            )
            .into(holder.imageView)
    }

    override fun getItemCount() = images.size

    fun updateImages(newImages: List<Image>) {
        images = newImages
    }
}