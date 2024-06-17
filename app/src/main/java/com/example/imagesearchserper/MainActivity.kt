package com.example.imagesearchserper

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.view.ImageAdapter
import com.example.imagesearchserper.viewModel.ImageViewModel

class MainActivity : ComponentActivity() {
    private lateinit var query: String
    private lateinit var searchField: EditText
    private var imagesList: List<Image> = emptyList()
    private val imageViewModel = ImageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchField = findViewById(R.id.searchFieldET)

        // Initialize the RecyclerView and the ImageAdapter with an empty list
        val recyclerView: RecyclerView = findViewById(R.id.imageListRV)
        val imageAdapter = ImageAdapter(imagesList)
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = imageAdapter

        // Observe the LiveData object in the ImageViewModel
        imageViewModel.imagesLiveData.observe(this) { images ->
            imageAdapter.updateImages(images)
            imagesList = images
        }

        searchField.setOnEditorActionListener { _, _, _ ->
            query = searchField.text.toString()
            searchImages(query)
            true
        }
    }

    private fun searchImages(query: String) {
        imageViewModel.getImages(query)
    }
}