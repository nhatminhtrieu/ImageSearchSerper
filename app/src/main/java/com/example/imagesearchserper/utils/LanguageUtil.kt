package com.example.imagesearchserper.utils

import android.content.Context

object LanguageUtil {
    fun getAppLanguage(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        return sharedPreferences.getString("App_lang", "en") ?: "en" // English is the default language
    }
}