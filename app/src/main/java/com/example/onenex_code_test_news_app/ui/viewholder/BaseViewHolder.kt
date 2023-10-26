package com.example.onenex_code_test_news_app.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Hsu Myat Ye Htet on 26/10/2023 01:39 AM
 */
abstract class BaseViewHolder<W>(itemView: View): RecyclerView.ViewHolder(itemView) {

    protected var mData: W? = null

    abstract fun bindData(data: W)
}