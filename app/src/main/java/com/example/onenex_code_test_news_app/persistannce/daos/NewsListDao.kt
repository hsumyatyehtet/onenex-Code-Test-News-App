package com.example.onenex_code_test_news_app.persistannce.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import io.reactivex.Completable

@Dao
interface NewsListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(userList: List<ArticleVO>): Completable

    @Query("select * from news")
    fun getUserList(): LiveData<List<ArticleVO>>

//    @Query("select * from user_list where id =:userId")
//    fun getUserByUserId(userId: Int): LiveData<ArticleVO>
//
//    @Query("select * from user_list where name like :searchWord")
//    fun retrieveUserListBySearch(searchWord: String): LiveData<List<ArticleVO>>

}