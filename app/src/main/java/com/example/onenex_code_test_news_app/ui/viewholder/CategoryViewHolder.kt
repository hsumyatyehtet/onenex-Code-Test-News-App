package com.example.onenex_code_test_news_app.ui.viewholder

import android.view.View
import com.example.onenex_code_test_news_app.R
import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.databinding.ViewHolerInCategoryBinding
import com.example.onenex_code_test_news_app.ui.adapter.CategoryListAdapter

class CategoryViewHolder(
    private val binding: ViewHolerInCategoryBinding,
    private val delegate: CategoryListAdapter.Delegate
) : BaseViewHolder<CategoryVO>(binding.root) {

    init {


        itemView.setOnClickListener {
            mData?.let {categoryVO ->
                delegate.onTapItem(categoryVO)
            }
        }
    }

    override fun bindData(data: CategoryVO) {

        if (data.isSelected){
            binding.tvCategoryName.setTextColor(itemView.resources.getColor(R.color.colorPurple500))
            binding.viewSelect.visibility = View.VISIBLE
        }
        else{
            binding.tvCategoryName.setTextColor(itemView.resources.getColor(R.color.colorSecondaryText))
            binding.viewSelect.visibility = View.GONE
        }

        mData = data

        binding.tvCategoryName.text = data.name
    }

}