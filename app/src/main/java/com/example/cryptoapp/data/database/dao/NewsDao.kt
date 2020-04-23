package com.example.cryptoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoapp.data.model.News
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publishedTime DESC")
    fun getNewsList(): Flowable<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsList(priceList: List<News>): Completable

}
