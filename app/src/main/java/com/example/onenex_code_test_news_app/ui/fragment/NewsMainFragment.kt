package com.example.onenex_code_test_news_app.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onenex_code_test_news_app.R
import com.example.onenex_code_test_news_app.data.vos.CategoryVO
import com.example.onenex_code_test_news_app.databinding.FragmentNewsMainBinding
import com.example.onenex_code_test_news_app.ui.adapter.CategoryListAdapter
import com.example.onenex_code_test_news_app.ui.adapter.NewsListAdapter
import com.example.onenex_code_test_news_app.utils.API_KEY_DATA
import com.example.onenex_code_test_news_app.utils.QUERY_DATA_SOURCE
import com.example.onenex_code_test_news_app.utils.StatefulData
import com.example.onenex_code_test_news_app.utils.getCategoryList
import com.example.onenex_code_test_news_app.viewmodel.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsMainFragment : BaseFragment(),CategoryListAdapter.Delegate,NewsListAdapter.Delegate{

    private lateinit var binding: FragmentNewsMainBinding

    private val viewModel by viewModels<NewsListViewModel>()

    private lateinit var mCategoryAdapter: CategoryListAdapter
    private lateinit var mNewsListAdapter: NewsListAdapter

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
      //  loadData()
        setUpDataBinding()

    }

    private fun setUpDataBinding() {

        var categoryList = getCategoryList()

        categoryList.map {
            if (it.id == 1){
                it.isSelected = true
                lifecycleScope.launch {
                    viewModel.loadNewsList(it.key, API_KEY_DATA)
                }
            }
        }

        mCategoryAdapter.setNewData(categoryList)

        viewModel.articleList.observe(viewLifecycleOwner){
            when(it.state){
                StatefulData.DataState.ERROR -> {
                    Log.d("errorMessage",it.message.toString())
                }
                StatefulData.DataState.SUCCESS -> {
                    it.data?.let {articleList->
                        mNewsListAdapter.setNewData(articleList.toMutableList())
                    }
                }

                else -> {}
            }
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.loadNewsList(QUERY_DATA_SOURCE, API_KEY_DATA)
        }
    }

    private fun setUpRecyclerView() {
        mCategoryAdapter = CategoryListAdapter(this)
        binding.rvMain.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvMain.adapter = mCategoryAdapter


        mNewsListAdapter = NewsListAdapter(this)
        binding.rvNewsList.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvNewsList.adapter = mNewsListAdapter


    }

    override fun onTapItem(data: CategoryVO) {

        var mDataList = getCategoryList()

        mDataList.forEach {
            if (it.id == data.id){
                it.isSelected = true
            }
        }

        mCategoryAdapter.setNewData(mDataList)

        lifecycleScope.launch {
            viewModel.loadNewsList(data.key, API_KEY_DATA)
        }

    }

    override fun onTapItem() {
        findNavController().navigate(R.id.action_navNews_to_newsDetailFragment)
    }

}