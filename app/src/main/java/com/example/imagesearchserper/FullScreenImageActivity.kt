package com.example.imagesearchserper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.viewpager.widget.ViewPager
import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.view.ImagePagerAdapter

class FullScreenImageActivity : ComponentActivity() {
    private lateinit var images: List<Image>
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        images = intent.getParcelableArrayListExtra<Image>("images") ?: emptyList()
        position = intent.getIntExtra("position", 0)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ImagePagerAdapter(images, this)
        viewPager.currentItem = position

        val openSourceButton: Button = findViewById(R.id.openSourceBtn)
        openSourceButton.setOnClickListener {
            val url = images[viewPager.currentItem].link
            if (URLUtil.isValidUrl(url)) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid URL: $url", Toast.LENGTH_SHORT).show()
            }
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Not implemented
            }

            override fun onPageSelected(position: Int) {
                openSourceButton.elevation = 0f
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Not implemented
            }
        })
    }
}