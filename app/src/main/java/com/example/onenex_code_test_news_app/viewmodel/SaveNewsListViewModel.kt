package com.example.onenex_code_test_news_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onenex_code_test_news_app.data.repository.NewsRepository
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.utils.StatefulData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveNewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _savedArticleList: MutableLiveData<StatefulData<List<ArticleVO>>> = MutableLiveData(
        StatefulData.loading()
    )
    val saveArticleList: LiveData<StatefulData<List<ArticleVO>>> = _savedArticleList

    init {

    }

    suspend fun loadSaveNewsList() {
        viewModelScope.launch {
            var loadNews = async { getSavesNewsList() }
            loadNews.await()
        }
    }

    private suspend fun getSavesNewsList() {

        newsRepository.getSavedNewsList().collect {
            _savedArticleList.postValue(it)
            Log.d("test",it.data.toString())
        }

    }

    fun onTapSaveNews(articleVO: ArticleVO){
        newsRepository.setSaveItem(articleVO.id, !articleVO.isSave)
    }

}