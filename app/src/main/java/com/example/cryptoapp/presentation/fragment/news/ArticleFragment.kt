package com.example.cryptoapp.presentation.fragment.news

import android.os.Bundle
import com.example.cryptoapp.R
import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.presentation.UiConstants.ARGUMENT_NEWS
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.presentation.viewmodel.news.ArticleViewModel
import com.example.cryptoapp.toolchain.getScreenWidth
import com.example.cryptoapp.toolchain.getTimeDifference
import com.example.cryptoapp.toolchain.getViewHeightPx
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : BaseFragment<ArticleViewModel, HomeSharedViewModel>() {
    private var news: News? = null

    override fun getLayoutRes(): Int = R.layout.fragment_article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = it.getParcelable(ARGUMENT_NEWS)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvAuthorNews.text = news?.sourceInfo?.sourceName
        news?.sourceInfo?.sourceImage?.let {
            if (it.isNotBlank())
                Picasso.get()
                    .load(it)
                    .into(ivAuthorLogo)
        }

        tvTimeAgo.text = getTimeDifference(news?.publishedTime)
        tvTitle.text = news?.title
        tvBodyArticleText.text = news?.textArticle

        Picasso.get()
            .load(news?.imageUrl)
            .resize(getScreenWidth(), getViewHeightPx(ivPoster))
            .into(ivPoster)
    }

    companion object {
        @JvmStatic
        fun newInstance(news: News) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGUMENT_NEWS, news)
                }
            }
    }

}
