package com.example.cryptoapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetNewsResponse : BaseApiResponse<List<News>>()

data class News(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("guid")
    @Expose
    val sourceShortLink: String?,
    @SerializedName("published_on")
    @Expose
    val publishedTime: Long,
    @SerializedName("imageurl")
    @Expose
    val imageUrl: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("url")
    @Expose
    val sourceUrl: String?,
    @SerializedName("body")
    @Expose
    val textArticle: String?,
    @SerializedName("source_info")
    @Expose
    val sourceInfo: NewsSourceInfo?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        sourceShortLink = parcel.readString(),
        publishedTime = parcel.readLong(),
        imageUrl = parcel.readString(),
        title = parcel.readString(),
        sourceUrl = parcel.readString(),
        textArticle = parcel.readString(),
        sourceInfo = parcel.readParcelable(NewsSourceInfo::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(sourceShortLink)
        parcel.writeLong(publishedTime)
        parcel.writeString(imageUrl)
        parcel.writeString(title)
        parcel.writeString(sourceUrl)
        parcel.writeString(textArticle)
        parcel.writeParcelable(sourceInfo, flags)
    }

    override fun describeContents(): Int = 0


    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News = News(parcel)

        override fun newArray(size: Int): Array<News?> = arrayOfNulls(size)

    }
}

class NewsSourceInfo(
    @SerializedName("name")
    @Expose
    val sourceName: String? = null,
    @SerializedName("lang")
    @Expose
    val language: String? = null,
    @SerializedName("img")
    val sourceImage: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sourceName)
        parcel.writeString(language)
        parcel.writeString(sourceImage)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<NewsSourceInfo> {
        override fun createFromParcel(parcel: Parcel): NewsSourceInfo = NewsSourceInfo(parcel)

        override fun newArray(size: Int): Array<NewsSourceInfo?> = arrayOfNulls(size)
    }

}