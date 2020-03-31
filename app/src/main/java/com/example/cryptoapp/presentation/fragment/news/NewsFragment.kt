package com.example.cryptoapp.presentation.fragment.news

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.App
import com.example.cryptoapp.presentation.adapter.NewsAdapter
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.presentation.viewmodel.news.NewsListViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment<NewsListViewModel, HomeSharedViewModel>() {
    private val newsAdapter = NewsAdapter(App.context)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvNews.adapter = newsAdapter
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            newsAdapter.newsList = it
        })
    }

    override fun getLayoutRes(): Int = R.layout.fragment_news

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}
