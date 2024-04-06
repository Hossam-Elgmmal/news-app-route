package com.route.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsapp.models.sources.SourcesItem

@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSources(sources: List<SourcesItem>)

    @Query("SELECT * FROM SourcesItem WHERE category = :categoryId")
    suspend fun getSources(categoryId: String): List<SourcesItem>

}