package com.example.onenex_code_test_news_app.persistannce.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import io.reactivex.Completable
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsList(articleList: List<ArticleVO>)

    @Query("select * from news")
    fun getNewsList(): Flow<List<ArticleVO>>

    @Query("UPDATE news SET isSave = :isSave where id = :newsId")
    fun setSaveNews(newsId: Int,isSave: Boolean)


}