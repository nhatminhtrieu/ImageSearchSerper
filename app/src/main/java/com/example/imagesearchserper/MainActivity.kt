package com.example.imagesearchserper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.view.ImageAdapter
import com.example.imagesearchserper.viewModel.ImageViewModel
import com.google.android.material.button.MaterialButton

class MainActivity : ComponentActivity() {
    private var query: String? = null
    private lateinit var searchField: SearchView
    private lateinit var settingsButton: Button
    private var imagesList: List<Image> = emptyList()
    private val imageViewModel = ImageViewModel()
    private var isSearchInProgress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchField = findViewById(R.id.searchFieldET)
        settingsButton = findViewById(R.id.settingsBtn)

        initializeRecyclerView()
        initializeSearchView()

        settingsButton.setOnClickListener {
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }
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