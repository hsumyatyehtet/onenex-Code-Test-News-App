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
import com.example.onenex_code_test_news_app.data.vos.response.ArticleVO
import com.example.onenex_code_test_news_app.databinding.FragmentNewsMainBinding
import com.example.onenex_code_test_news_app.databinding.FragmentSaveBinding
import com.example.onenex_code_test_news_app.ui.adapter.SavedNewsListAdapter
import com.example.onenex_code_test_news_app.utils.StatefulData
import com.example.onenex_code_test_news_app.utils.getBundleNewsDetail
import com.example.onenex_code_test_news_app.utils.getCategoryList
import com.example.onenex_code_test_news_app.viewmodel.SaveNewsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveFragment : BaseFragment(), SavedNewsListAdapter.Delegate {

    private lateinit var binding: FragmentSaveBinding

    private val viewModel by viewModels<SaveNewsListViewModel>()

    private lateinit var mSavedNewsAdapter: SavedNewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        setUpDataObservation()
        setUpDataBinding()
    }

    private fun setUpDataBinding() {
        viewModel.saveArticleList.observe(viewLifecycleOwner) {
            when (it.state) {
                StatefulData.DataState.LOADING -> {

                }

                StatefulData.DataState.ERROR -> {

                    Log.d("errorMessage", it.message.toString())
                }

                StatefulData.DataState.SUCCESS -> {

                    it.data?.let { articleList ->

                        mSavedNewsAdapter.setNewData(articleList.toMutableList())
                    }
                }

                StatefulData.DataState.COMPLETED -> {

                }
            }
        }
    }


    private fun setUpDataObservation() {
        lifecycleScope.launch {
            viewModel.loadSaveNewsList()
        }
    }

    private fun setUpRecyclerView() {

        mSavedNewsAdapter = SavedNewsListAdapter(this)
        binding.rvSavedNewsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSavedNewsList.adapter = mSavedNewsAdapter


    }

    override fun onTapItem(title: String, url: String) {
        findNavController().navigate(
            R.id.action_navSave_to_newsDetailFragment,
            getBundleNewsDetail(title = title, url = url),)
    }

    override fun onTapSaveItem(articleVO: ArticleVO) {
        viewModel.onTapSaveNews(articleVO = articleVO)
    }

}