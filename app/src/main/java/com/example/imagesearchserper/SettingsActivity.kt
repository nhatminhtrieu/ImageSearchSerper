package com.example.imagesearchserper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.io.File
import java.util.Locale

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        loadLocale()

        val clearCacheButton: Button = findViewById(R.id.clearCacheBtn)
        clearCacheButton.setOnClickListener {
            clearAppCache()
        }

        val changeLanguageButton: Button = findViewById(R.id.changeLanguageBtn)
        changeLanguageButton.setOnClickListener {
            showChangeLanguageDialog()
        }

        val backButton: Button = findViewById(R.id.backToMainBtn)
        backButton.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("languageChanged", true)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun clearAppCache() {
        try {
            Thread {
                Glide.get(applicationContext).clearDiskCache()
            }.start()

            runOnUiThread {
                Glide.get(applicationContext).clearMemory()
            }

            val dir = this.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        val success: Boolean

        fun recursiveDelete(file: File): Boolean {
            if (file.isDirectory) {
                file.listFiles()?.forEach {
                    if (!recursiveDelete(it)) return false
                }
            }
            return file.delete()
        }

        success = if (dir != null) {
            recursiveDelete(dir)
        } else {
            false
        }

        val messageResId = if (success) R.string.cache_clear_msg_success else R.string.cache_clear_msg_failure
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        return success
    }


    private fun showChangeLanguageDialog() {
        val listItems = arrayOf(getString(R.string.english), getString(R.string.vietnamese))
        val mBuilder = AlertDialog.Builder(this@SettingsActivity)
        mBuilder.setTitle(getString(R.string.choose_language))

        val currentLanguage = sharedPreferences.getString("App_lang", "en")
        val checkedItem = if (currentLanguage == "en") 0 else 1

        mBuilder.setSingleChoiceItems(listItems, checkedItem) { dialogInterface: DialogInterface, i: Int ->
            if (i == 0) {
                setLocale("en") // English
                recreate()
            } else if (i == 1) {
                setLocale("vi") // Vietnamese
                recreate()
            }
            dialogInterface.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocale(lang: String) {
        val locale = if (lang == "en") Locale("en", "US") else Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        val editor = sharedPreferences.edit()
        editor.putString("App_lang", lang)
        editor.apply()
    }

    private fun loadLocale() {
        val language =
            sharedPreferences.getString("App_lang", "en")
        if (language != null) {
            setLocale(language)
        }
    }
}