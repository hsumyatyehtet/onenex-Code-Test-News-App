package com.example.onenex_code_test_news_app.ui.viewholder

import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.databinding.ViewHolerInCategoryBinding

class CategoryViewHolder(
    private val binding: ViewHolerInCategoryBinding
) : BaseViewHolder<CategoryVO>(binding.root) {

    override fun bindData(data: CategoryVO) {
        binding.tvCategoryName.text = data.name
    }

}