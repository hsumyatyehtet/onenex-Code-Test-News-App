package com.example.onenex_code_test_news_app.network

import com.example.onenex_code_test_news_app.data.vos.response.NewsResponse
import com.example.onenex_code_test_news_app.utils.RemoteDataSource
import com.example.onenex_code_test_news_app.utils.StatefulData
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val api: NewsListApi) : RemoteDataSource() {

    suspend fun loadNewsList(source: String,apkKey: String): StatefulData<NewsResponse> = getResponse(errorMessage = "Unable to load News"){
        api.loadNewsList(source, apkKey)
    }

}