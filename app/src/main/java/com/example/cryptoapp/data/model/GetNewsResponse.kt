package com.example.cryptoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetNewsResponse : BaseApiResponse<List<News>>()

data class News(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("guid")
    @Expose
    val sourceShortLink: String,
    @SerializedName("published_on")
    @Expose
    val publishedTime: Long,
    @SerializedName("imageurl")
    @Expose
    val imageUrl: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("url")
    @Expose
    val sourceUrl: String,
    @SerializedName("body")
    @Expose
    val textArticle: String,
    @SerializedName("source_info")
    @Expose
    val sourceInfo: NewsSourceInfo
)

class NewsSourceInfo {
    @SerializedName("name")
    @Expose
    val sourceName: String? = null

    @SerializedName("lang")
    @Expose
    val language: String? = null

    @SerializedName("img")
    val sourceImage: String? = null
}