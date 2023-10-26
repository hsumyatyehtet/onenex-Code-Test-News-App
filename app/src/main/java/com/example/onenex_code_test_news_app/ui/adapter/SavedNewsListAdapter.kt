package com.example.onenex_code_test_news_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.databinding.ViewHolderInNewsListBinding
import com.example.onenex_code_test_news_app.ui.viewholder.NewsListViewHolder
import com.example.onenex_code_test_news_app.ui.viewholder.SavedNewsListViewHolder

class SavedNewsListAdapter() :BaseRecyclerAdapter<SavedNewsListViewHolder,CategoryVO>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsListViewHolder {
        return SavedNewsListViewHolder(
            ViewHolderInNewsListBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

}