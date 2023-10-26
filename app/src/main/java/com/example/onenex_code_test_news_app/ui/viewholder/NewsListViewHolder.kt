package com.example.onenex_code_test_news_app.ui.viewholder

import com.bumptech.glide.Glide
import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.databinding.ViewHolderInNewsListBinding
import com.example.onenex_code_test_news_app.ui.adapter.NewsListAdapter

class NewsListViewHolder(
    private val binding: ViewHolderInNewsListBinding,
    private val delegate: NewsListAdapter.Delegate
) : BaseViewHolder<ArticleVO>(binding.root) {

    init {
        itemView.setOnClickListener {
            delegate.onTapItem()
        }
    }

    override fun bindData(data: ArticleVO) {

        binding.tvTitle.text = data.title

        Glide.with(itemView.context)
            .load(data.urlToImage.toString())
            .into(binding.ivNewsItem)

    }

}