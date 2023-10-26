package com.example.onenex_code_test_news_app.ui.viewholder

import com.bumptech.glide.Glide
import com.example.onenex_code_test_news_app.R
import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.databinding.ViewHolderInNewsListBinding
import com.example.onenex_code_test_news_app.utils.getApiDateTime

class SavedNewsListViewHolder(
    private val binding: ViewHolderInNewsListBinding
) : BaseViewHolder<ArticleVO>(binding.root) {

    override fun bindData(data: ArticleVO) {

        mData = data

        binding.tvTitle.text = data.title

        Glide.with(itemView.context)
            .load(data.urlToImage.toString())
            .into(binding.ivNewsItem)
        binding.tvTime.text = getApiDateTime(data.publishedAt.toString())

        if (data.isSave){
            Glide.with(itemView.context)
                .load(R.drawable.baseline_bookmark_save)
                .into(binding.ivSave)
        }
        else{
            Glide.with(itemView.context)
                .load(R.drawable.baseline_bookmark)
                .into(binding.ivSave)
        }


    }

}