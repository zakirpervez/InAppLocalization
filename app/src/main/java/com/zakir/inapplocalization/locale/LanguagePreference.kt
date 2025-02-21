package com.zakir.inapplocalization.locale

import android.content.Context
import android.content.SharedPreferences

object LanguagePreference {
    private const val PREFS_NAME = "AppSettings"
    private const val LANGUAGE_CODE_KEY = "language_code"

    fun saveLanguagePreference(context: Context, languageCode: String) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        preferences.edit().putString(LANGUAGE_CODE_KEY, languageCode).apply()
    }

    fun getSavedLanguageCode(context: Context): String {
        val preferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getString(LANGUAGE_CODE_KEY, "en") ?: "en" // Default to English
    }
}