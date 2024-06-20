package com.example.imagesearchserper.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchserper.databinding.ImageItemBinding
import com.example.imagesearchserper.model.Image

class ImageAdapter(images: List<Image>) : RecyclerView.Adapter<ImageViewHolder>() {
    private val holders = mutableListOf<ImageViewHolder>()
    private var images: MutableList<Image> = images.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(inflater, parent, false)
        val holder = ImageViewHolder(binding, images)
        holders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount() = images.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateImages(newImages: List<Image>) {
        images = newImages.toMutableList()
        notifyDataSetChanged()
        for (holder in holders) {
            holder.updateImages(newImages)
        }
    }

    fun addImages(newImages: List<Image>) {
        val currentSize = images.size
        images.addAll(newImages)
        notifyItemRangeInserted(currentSize, newImages.size)
    }
}