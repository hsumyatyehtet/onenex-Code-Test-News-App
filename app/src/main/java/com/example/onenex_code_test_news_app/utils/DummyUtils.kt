package com.example.onenex_code_test_news_app.utils

import com.example.onenex_code_test_news_app.data.vos.CategoryVO

const val DB_NAME = "NewsList.db"

fun getCategoryList(): MutableList<CategoryVO>{
    return mutableListOf(
        CategoryVO(1,"Business","business",false),
        CategoryVO(2,"Entertainment","entertainment",false),
        CategoryVO(3,"General","general",false),
        CategoryVO(4,"Health","health",false),
        CategoryVO(5,"Science","science",false),
        CategoryVO(6,"Sports","sports",false),
        CategoryVO(7,"technology","technology",false)
    )
}