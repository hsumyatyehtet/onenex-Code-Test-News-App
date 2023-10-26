package com.example.onenex_code_test_news_app.data.vos.response

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//@JsonClass(generateAdapter = true)

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleVO>
)

//@Entity(tableName = "news", indices = [Index(value = ["title","publishedAt"], unique = true)])
@Entity(tableName = "news")
data class ArticleVO(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var author: String? = null,

    var title: String? = null,

    var url: String? = null,

    var urlToImage: String? = null,

    var publishedAt: String? = null,

    var category: String? = null,

    var isSave: Boolean = false
)