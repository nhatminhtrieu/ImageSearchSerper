package com.example.imagesearchserper

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.viewpager.widget.ViewPager
import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.utils.LanguageUtil
import com.example.imagesearchserper.view.ImagePagerAdapter
import java.util.Locale

@Suppress("DEPRECATION")
class FullScreenImageActivity : ComponentActivity() {
    private lateinit var images: List<Image>
    private var position: Int = 0
    private lateinit var imagePagerAdapter: ImagePagerAdapter
    private lateinit var language: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)
        language = LanguageUtil.getAppLanguage(this)
        images = intent.getParcelableArrayListExtra("imagesList") ?: emptyList()
        position = intent.getIntExtra("position", 0)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        imagePagerAdapter = ImagePagerAdapter(images, this)
        viewPager.adapter = imagePagerAdapter
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

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val languageChanged = data?.getBooleanExtra("languageChanged", false) ?: false
                if (languageChanged) {
                    val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
                    val language = sharedPreferences.getString("App_lang", "en")
                    if (language != null) {
                        setLocale(language)
                    }
                    recreate()
                }
            }
        }
    }

    @SuppressLint("AppBundleLocaleChanges")
    private fun setLocale(lang: String) {
        val locale = if (lang == "en") Locale("en", "US") else Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}