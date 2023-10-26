package com.example.onenex_code_test_news_app.data.vos.resquest

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsListRequest(
    val sources: String,
    val apiKey: String
)