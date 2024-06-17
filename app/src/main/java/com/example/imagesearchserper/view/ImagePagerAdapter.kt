package com.example.imagesearchserper.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.imagesearchserper.R
import com.example.imagesearchserper.model.Image

class ImagePagerAdapter(private val images: List<Image>, private val context: Context) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_full_screen_image, container, false)

        val imageView: ImageView = view.findViewById(R.id.fullScreenImageView)
        Glide.with(context).load(images[position].imageUrl).into(imageView)

        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}