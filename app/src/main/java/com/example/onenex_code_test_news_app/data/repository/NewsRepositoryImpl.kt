package com.example.onenex_code_test_news_app.data.repository

import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.network.NewsRemoteDataSource
import com.example.onenex_code_test_news_app.utils.StatefulData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
) : NewsRepository {

    override suspend fun getNews(
        source: String,
        apiKey: String
    ): Flow<StatefulData<List<ArticleVO>>> = flow<StatefulData<List<ArticleVO>>> {


        loadNews(source = source, apiKey = apiKey, this@flow)

    }.flowOn(Dispatchers.IO)

    private suspend fun loadNews(
        source: String,
        apiKey: String,
        collector: FlowCollector<StatefulData<List<ArticleVO>>>
    ) {

        var newsResponse = newsRemoteDataSource.loadNewsList(source, apiKey)

        when (newsResponse.state) {
            StatefulData.DataState.SUCCESS -> {
                var articleList: MutableList<ArticleVO> = mutableListOf()
                newsResponse.data?.articles?.let {
                    articleList.clear()
                    articleList.addAll(it)
                }
                //remain to save database
//                collector.emit(StatefulData.completed())
                collector.emit(StatefulData.success(articleList))
            }

            StatefulData.DataState.ERROR -> {
                collector.emit(StatefulData.error("Network failed, retrying..."))
                loadNews(source, apiKey, collector)
            }

            else -> collector.emit(StatefulData.loading())
        }

    }


}