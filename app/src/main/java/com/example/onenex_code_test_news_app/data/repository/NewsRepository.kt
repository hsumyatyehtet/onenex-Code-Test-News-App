package com.example.onenex_code_test_news_app.data.repository

import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.utils.StatefulData
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun getNews(source: String,apiKey:String): Flow<StatefulData<List<ArticleVO>>>

}