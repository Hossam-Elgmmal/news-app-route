package com.route.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.data.articles.ArticleItem
import com.route.data.sources.SourceItem

@Database(entities = [ArticleItem::class, SourceItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getSourcesDao(): SourcesDao
    abstract fun getArticlesDao(): ArticlesDao

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