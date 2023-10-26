package com.example.onenex_code_test_news_app.utils

import android.os.Bundle

fun getBundleNewsDetail(title: String): Bundle {
    return Bundle().apply {
        putString("news_title",title)
    }
}