package com.example.imagesearchserper

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
//import com.example.imagesearchserper.`object`.RetrofitInstance
import com.example.imagesearchserper.utils.ApiCallBack

class MainActivity : ComponentActivity() {
    private lateinit var query: String
    private lateinit var searchField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchField = findViewById(R.id.searchFieldET)

        searchField.setOnEditorActionListener { _, _, _ ->
            query = searchField.text.toString()
            Log.d("MainActivity", "Search query: $query")

            true
        }


    }
}