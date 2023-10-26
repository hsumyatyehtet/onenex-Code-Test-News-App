package com.example.onenex_code_test_news_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onenex_code_test_news_app.databinding.FragmentNewsMainBinding
import com.example.onenex_code_test_news_app.databinding.FragmentSaveBinding
import com.example.onenex_code_test_news_app.ui.adapter.SavedNewsListAdapter
import com.example.onenex_code_test_news_app.utils.getCategoryList

class SaveFragment : BaseFragment(){

    private lateinit var binding: FragmentSaveBinding

    private lateinit var mSavedNewsAdapter: SavedNewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        mSavedNewsAdapter = SavedNewsListAdapter()
        binding.rvSavedNewsList.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvSavedNewsList.adapter = mSavedNewsAdapter

        mSavedNewsAdapter.setNewData(getCategoryList())

    }

}