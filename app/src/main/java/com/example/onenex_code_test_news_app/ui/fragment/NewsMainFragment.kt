package com.example.onenex_code_test_news_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onenex_code_test_news_app.databinding.FragmentNewsMainBinding
import com.example.onenex_code_test_news_app.ui.adapter.CategoryListAdapter
import com.example.onenex_code_test_news_app.utils.getCategoryList

class NewsMainFragment : BaseFragment(){

    private lateinit var binding: FragmentNewsMainBinding

    private lateinit var mCategoryAdapter: CategoryListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsMainBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        mCategoryAdapter = CategoryListAdapter()
        binding.rvMain.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvMain.adapter = mCategoryAdapter

        mCategoryAdapter.setNewData(getCategoryList())

    }

}