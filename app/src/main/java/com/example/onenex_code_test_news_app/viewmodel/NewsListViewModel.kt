package com.example.onenex_code_test_news_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onenex_code_test_news_app.data.repository.NewsRepository
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.utils.StatefulData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(){

    private val _articleList: MutableLiveData<StatefulData<List<ArticleVO>>> = MutableLiveData(StatefulData.loading())
    val articleList: LiveData<StatefulData<List<ArticleVO>>> = _articleList



    suspend fun loadNewsList(source: String,apkKey: String){

        viewModelScope.launch {

            var loadNews = async { loadNewsListFromApi(source, apkKey) }

            loadNews.await()
        }

    }

    private suspend fun loadNewsListFromApi(source: String,apkKey: String){
        repository.getNews(source,apkKey)
            .collect{
                _articleList.postValue(it)
            }
    }

    fun onTapSaveNews(articleVO: ArticleVO){
        repository.setSaveItem(articleVO.id, !articleVO.isSave)
    }

}