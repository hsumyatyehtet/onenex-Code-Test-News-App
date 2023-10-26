package com.example.onenex_code_test_news_app.network

import com.example.onenex_code_test_news_app.data.vos.response.NewsResponse
import com.example.onenex_code_test_news_app.utils.GET_NEW_LIST
import com.example.onenex_code_test_news_app.utils.PARAM_API_KEY
import com.example.onenex_code_test_news_app.utils.PARAM_SORUCES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsListApi {

    @GET(GET_NEW_LIST)
    suspend fun loadNewsList(
        @Query(PARAM_SORUCES) source: String,
        @Query(PARAM_API_KEY) apkKey: String
    ): Response<NewsResponse>

}