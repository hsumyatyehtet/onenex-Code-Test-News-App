package com.example.onenex_code_test_news_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.onenex_code_test_news_app.databinding.FragmentNewsDetailBinding

class NewsDetailFragment: BaseFragment() {

    private lateinit var binding: FragmentNewsDetailBinding

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListener()
        setUpDataObservation()
    }

    private fun setUpListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpDataObservation() {
        var title = args.newsTitle
        var url = args.newsUrl

        binding.tvNewsTitle.text = title.toString()

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(url)
    }

}