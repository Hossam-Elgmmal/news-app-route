package com.route.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.route.data.articles.ArticleItem

@Dao
interface ArticlesDao {

    @Upsert
    suspend fun saveArticles(articleList: List<ArticleItem>)

    @Query("select * from articleitem where sourcesId = :sourcesId")
    suspend fun getArticles(sourcesId: String): List<ArticleItem>

    @Query("select * from articleitem where title = :title")
    suspend fun getArticleDetails(title: String): ArticleItem
}