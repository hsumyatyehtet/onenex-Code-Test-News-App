package com.example.onenex_code_test_news_app.utils

import com.example.onenex_code_test_news_app.data.vos.CategoryVO


fun getCategoryList(): MutableList<CategoryVO>{
    return mutableListOf(
        CategoryVO(1,"Business",false),
        CategoryVO(2,"Entartainment",false),
        CategoryVO(3,"Health",false)
    )
}