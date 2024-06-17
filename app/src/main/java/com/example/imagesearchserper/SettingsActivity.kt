package com.example.imagesearchserper

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val clearCacheButton: Button = findViewById(R.id.clearCacheBtn)
        clearCacheButton.setOnClickListener {
            clearAppCache()
        }
    }

    private fun clearAppCache() {
        try {
            val dir = this.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            if (children != null) {
                for (i in children.indices) {
                    val success = deleteDir(File(dir, children[i]))
                    if (!success) {
                        Toast.makeText(this, "Failed to clear cache", Toast.LENGTH_SHORT).show()
                        return false
                    }
                }
            }
            Toast.makeText(this, "Cache cleared", Toast.LENGTH_SHORT).show()
            return dir.delete()
        } else if (dir != null && dir.isFile) {
            Toast.makeText(this, "Failed to clear cache", Toast.LENGTH_SHORT).show()
            return dir.delete()
        } else {
            Toast.makeText(this, "Failed to clear cache", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}