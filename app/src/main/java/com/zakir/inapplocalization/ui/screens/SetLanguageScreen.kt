package com.zakir.inapplocalization.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zakir.inapplocalization.R
import com.zakir.inapplocalization.extension.changeLanguage
import com.zakir.inapplocalization.locale.LanguagePreference
import com.zakir.inapplocalization.locale.LocaleHelper
import com.zakir.inapplocalization.ui.composables.RememberUpdatedLanguageContext
import com.zakir.inapplocalization.ui.theme.InAppLocalizationTheme
import com.zakir.inapplocalization.util.getLanguageCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetLanguageScreen(modifier: Modifier) {
    val context = LocalContext.current
    val supportedLanguages = listOf("English", "Urdu", "Hindi")
    var currentLanguage by rememberSaveable {
        mutableStateOf(
            LanguagePreference.getSavedLanguageCode(context)
        )
    }
    val updatedLanguageContext = RememberUpdatedLanguageContext(currentLanguage)

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember {
        mutableStateOf(
            when (currentLanguage) {
                "ur" -> supportedLanguages[1]
                "hi" -> supportedLanguages[2]
                else -> supportedLanguages[0]
            }
        )
    }

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color.Magenta, Color.Cyan, Color.Yellow)
    )
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = updatedLanguageContext.getString(R.string.language),
            style = TextStyle(
                brush = gradientBrush,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select an option") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown arrow"
                    )
                },
                modifier = Modifier.menuAnchor() // Anchor for the dropdown menu
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                supportedLanguages.forEach { language ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            selectedItem = language
                            currentLanguage = getLanguageCode(language)
                            context.changeLanguage(currentLanguage)
                        },
                        text = { Text(text = language) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetLanguageScreenPreview() {
    InAppLocalizationTheme {
        SetLanguageScreen(modifier = Modifier)
    }
}