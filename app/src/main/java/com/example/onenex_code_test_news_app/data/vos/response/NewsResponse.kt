package com.example.onenex_code_test_news_app.data.vos.response

//@JsonClass(generateAdapter = true)
data class NewsResponse(
    val status:  String,
    val totalResults: Int,
    val articles: List<ArticleVO>
)

data class ArticleVO(
    val author: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String
)