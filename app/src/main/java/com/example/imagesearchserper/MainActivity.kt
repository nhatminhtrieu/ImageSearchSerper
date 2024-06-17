package com.example.imagesearchserper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.view.ImageAdapter
import com.example.imagesearchserper.viewModel.ImageViewModel

class MainActivity : ComponentActivity() {
    private var query: String? = null
    private lateinit var searchField: SearchView
    private var imagesList: List<Image> = emptyList()
    private val imageViewModel = ImageViewModel()
    private var isSearchInProgress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchField = findViewById(R.id.searchFieldET)

        initializeRecyclerView()
        initializeSearchView()
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