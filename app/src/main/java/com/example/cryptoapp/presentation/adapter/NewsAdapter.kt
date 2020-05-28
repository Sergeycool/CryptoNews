package com.example.cryptoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.toolchain.getTimeDifference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var newsList: List<News> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onNewsClickListener: OnNewsClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        with(holder) {
            with(news) {
                tvTitle.text = title
                tvPublishedDate.text = getTimeDifference(publishedTime)
                Picasso.get().load(imageUrl).into(ivArticleImage)
                itemView.setOnClickListener {
                    onNewsClickListener?.onNewsClick(this)
                }
            }
        }
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivArticleImage: ImageView = itemView.imageArticle
        val tvTitle: TextView = itemView.tvTitleNews
        val tvPublishedDate: TextView = itemView.tvNewsPublishedTime
    }

    interface OnNewsClick {
        fun onNewsClick(news: News)
    }
}
