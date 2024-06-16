package com.example.imagesearchserper

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesearchserper.view.ImageAdapter
import com.example.imagesearchserper.viewModel.ImageViewModel

class MainActivity : ComponentActivity() {
    private lateinit var query: String
    private lateinit var searchField: EditText
    private val imageViewModel = ImageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchField = findViewById(R.id.searchFieldET)

        // Initialize the RecyclerView and the ImageAdapter with an empty list
        val recyclerView: RecyclerView = findViewById(R.id.imageListRV)
        val imageAdapter = ImageAdapter(listOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = imageAdapter

        // Observe the LiveData object in the ImageViewModel
        imageViewModel.imagesLiveData.observe(this) { images ->
            // Update the adapter's data when the LiveData changes
            imageAdapter.updateImages(images)
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