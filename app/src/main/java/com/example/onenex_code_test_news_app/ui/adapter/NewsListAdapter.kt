package com.example.onenex_code_test_news_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.databinding.ViewHolderInNewsListBinding
import com.example.onenex_code_test_news_app.ui.viewholder.NewsListViewHolder

class NewsListAdapter(val delegate: Delegate) :BaseRecyclerAdapter<NewsListViewHolder,ArticleVO>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            ViewHolderInNewsListBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            ),
            delegate
        )
    }

    interface Delegate{
        fun onTapItem()
        fun onTapSaveItem(articleVO: ArticleVO)
    }

}