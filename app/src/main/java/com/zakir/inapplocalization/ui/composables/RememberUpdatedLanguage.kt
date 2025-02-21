package com.zakir.inapplocalization.ui.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.zakir.inapplocalization.locale.LocaleHelper

@Composable
fun RememberUpdatedLanguageContext(languageCode: String): Context {
    val context = LocalContext.current
    return remember(languageCode) {
        LocaleHelper.setLocale(context, languageCode)
    }
}