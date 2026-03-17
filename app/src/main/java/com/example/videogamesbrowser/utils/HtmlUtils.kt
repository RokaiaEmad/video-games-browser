package com.example.videogamesbrowser.utils

import androidx.core.text.HtmlCompat

fun parseHtmlDescription(description: String): String {
    return HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        .toString()
        .trim()
        .ifEmpty { "No description available" }
}