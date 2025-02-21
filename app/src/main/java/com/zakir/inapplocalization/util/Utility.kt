package com.zakir.inapplocalization.util

fun getLanguageCode(language: String): String {
    return when (language) {
        "Hindi" -> "hi"
        "Urdu" -> "ur"
        else -> "en"
    }
}