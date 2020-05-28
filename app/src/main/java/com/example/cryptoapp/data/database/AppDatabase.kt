package com.example.cryptoapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cryptoapp.data.DataConstants
import com.example.cryptoapp.data.database.dao.CoinPriceInfoDao
import com.example.cryptoapp.data.database.dao.NewsDao
import com.example.cryptoapp.data.database.typeconverter.Converters
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.data.model.News

@Database(entities = [CoinPriceInfo::class, News::class],
          version = DataConstants.DATABASE_VERSION,
          exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = DataConstants.DATABASE_NAME
        @Volatile
        private var LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

    abstract fun newsDao(): NewsDao
}
