package com.example.onenex_code_test_news_app.data.repository

import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.network.NewsRemoteDataSource
import com.example.onenex_code_test_news_app.persistannce.NewsListDatabase
import com.example.onenex_code_test_news_app.utils.StatefulData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepositoryImpl @Inject constructor(
    private val newsListDatabase: NewsListDatabase,
    private val newsRemoteDataSource: NewsRemoteDataSource,
) : NewsRepository {

    override suspend fun getNews(
        source: String,
        apiKey: String
    ): Flow<StatefulData<List<ArticleVO>>> = flow {

        loadNews(source = source, apiKey = apiKey, this@flow)

        newsListDatabase.newsListDao().getNewsList().collect{articleList->

            var articleListByCategory = articleList.filter { it.category == source }

            if (articleListByCategory.isNotEmpty()) emit(StatefulData.success(articleListByCategory))
            else if (articleListByCategory.isEmpty()) emit(StatefulData.error("Unable to upload news"))
        }

    }.flowOn(Dispatchers.IO)

    override fun setSaveItem(newsId: Int, isSaved: Boolean) {
        newsListDatabase.newsListDao().setSaveNews(newsId = newsId,isSaved)
    }

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

                saveArticleList(source,articleList)

                collector.emit(StatefulData.completed())
            }

            StatefulData.DataState.ERROR -> {
                collector.emit(StatefulData.error("Network failed, retrying..."))
                loadNews(source, apiKey, collector)
            }

            else -> collector.emit(StatefulData.loading())
        }

    }

    private fun saveArticleList(source: String, articleList: MutableList<ArticleVO>) {

        articleList.map {
            it.category = source
        }

        newsListDatabase.newsListDao().insertNewsList(articleList)
    }


}