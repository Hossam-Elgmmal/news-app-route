package com.route.newsapp.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.route.newsapp.models.sources.SourceItem

@Dao
interface SourcesDao {

    @Upsert
    suspend fun saveSources(sources: List<SourceItem>)

    @Query("SELECT * FROM SourceItem WHERE category = :categoryId")
    suspend fun getSources(categoryId: String): List<SourceItem>

}