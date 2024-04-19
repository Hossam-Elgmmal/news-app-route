package com.route.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsapp.models.articles.ArticlesItem
import com.route.newsapp.models.sources.SourceItem

@Database(entities = [ArticlesItem::class, SourceItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getDao(): SourcesDao

    companion object {
        private var INSTANCE: NewsDatabase? = null
        private const val DATABASE_NAME = "NEWS_DATABASE"
        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, NewsDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): NewsDatabase {
            return INSTANCE!!
        }
    }

}