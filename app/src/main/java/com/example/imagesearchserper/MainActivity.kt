package com.example.imagesearchserper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.utils.LanguageUtil
import com.example.imagesearchserper.view.ImageAdapter
import com.example.imagesearchserper.viewModel.ImageViewModel
import com.google.android.material.button.MaterialButton
import java.util.Locale

class MainActivity : ComponentActivity() {
    private var query: String? = null
    private lateinit var searchField: SearchView
    private lateinit var settingsButton: Button
    private var imagesList: List<Image> = emptyList()
    private val imageViewModel = ImageViewModel()
    private var isSearchInProgress = false
    private lateinit var language: String

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("query", query)
        outState.putParcelableArrayList("imagesList", ArrayList(imagesList))
        outState.putBoolean("isSearchInProgress", isSearchInProgress)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        language = LanguageUtil.getAppLanguage(this)
        searchField = findViewById(R.id.searchFieldET)
        settingsButton = findViewById(R.id.settingsBtn)

        if (savedInstanceState != null) {
            query = savedInstanceState.getString("query")
            imagesList = savedInstanceState.getParcelableArrayList("imagesList") ?: emptyList()
            isSearchInProgress = savedInstanceState.getBoolean("isSearchInProgress")
        }

        initializeRecyclerView()
        initializeSearchView()

        settingsButton.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivityForResult(settingsIntent, 1)
        }
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

    private fun setLocale(lang: String) {
        val locale = if (lang == "en") Locale("en", "US") else Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun initializeRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.imageListRV)
        val imageAdapter = ImageAdapter(imagesList)
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = imageAdapter

        imageViewModel.imagesLiveData.observe(this) { images ->
            imageAdapter.updateImages(images)
            imagesList = images
            isSearchInProgress = false
        }
    }

    private fun initializeSearchView() {
        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                this@MainActivity.query = query
                if (query != null) {
                    searchImages(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchImages(query: String) {
        if (isSearchInProgress) {
            return
        }
        isSearchInProgress = true
        imageViewModel.getImages(query)
    }
}