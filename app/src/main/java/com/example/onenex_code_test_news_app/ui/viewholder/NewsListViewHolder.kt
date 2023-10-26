package com.example.onenex_code_test_news_app.ui.viewholder

import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.databinding.ViewHolderInNewsListBinding
import com.example.onenex_code_test_news_app.ui.adapter.NewsListAdapter

class NewsListViewHolder(
    private val binding: ViewHolderInNewsListBinding,
    private val delegate: NewsListAdapter.Delegate
) : BaseViewHolder<CategoryVO>(binding.root) {

    init {
        itemView.setOnClickListener {
            delegate.onTapItem()
        }
    }

    override fun bindData(data: CategoryVO) {

    }

}