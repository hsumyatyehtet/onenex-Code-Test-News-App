package com.example.onenex_code_test_news_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.databinding.ViewHolerInCategoryBinding
import com.example.onenex_code_test_news_app.ui.viewholder.CategoryViewHolder

class CategoryListAdapter() :BaseRecyclerAdapter<CategoryViewHolder,CategoryVO>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ViewHolerInCategoryBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

}