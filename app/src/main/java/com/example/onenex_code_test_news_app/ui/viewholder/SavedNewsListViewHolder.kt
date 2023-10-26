package com.example.onenex_code_test_news_app.ui.viewholder

import com.example.onenex_code_test_news_app.R
import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.databinding.ViewHolderInNewsListBinding

class SavedNewsListViewHolder(
    private val binding: ViewHolderInNewsListBinding
) : BaseViewHolder<CategoryVO>(binding.root) {

    override fun bindData(data: CategoryVO) {

        binding.ivSave.setImageDrawable(itemView.resources.getDrawable(R.drawable.baseline_bookmark_save))

    }

}