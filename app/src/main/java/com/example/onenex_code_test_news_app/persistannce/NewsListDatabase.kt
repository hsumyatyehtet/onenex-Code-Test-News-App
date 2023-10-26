package com.example.onenex_code_test_news_app.persistannce

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.persistannce.daos.NewsListDao
import com.example.onenex_code_test_news_app.utils.DB_NAME

@Database(
    entities = [ArticleVO::class],
    version = 1,
    exportSchema = false
)
abstract class NewsListDatabase : RoomDatabase() {

    abstract fun newsListDao(): NewsListDao

    companion object {

        @Volatile
        private var INSTANCE: NewsListDatabase? = null

        fun getDatabase(context: Context): NewsListDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsListDatabase::class.java, DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }

}