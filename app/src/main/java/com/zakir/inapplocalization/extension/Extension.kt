package com.zakir.inapplocalization.extension

import android.content.Context
import com.zakir.inapplocalization.locale.LanguagePreference
import com.zakir.inapplocalization.locale.LocaleHelper

fun Context.changeLanguage(languageCode: String) {
    LanguagePreference.saveLanguagePreference(this, languageCode)
    LocaleHelper.setLocale(this, languageCode)
}