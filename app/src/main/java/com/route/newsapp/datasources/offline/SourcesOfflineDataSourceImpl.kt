package com.route.newsapp.datasources.offline

import android.util.Log
import com.route.newsapp.contarcts.SourcesOfflineDataSource
import com.route.newsapp.database.SourcesDao
import com.route.newsapp.models.sources.SourcesItem

private const val TAG = "SourcesOfflineDataSourceImpl"

class SourcesOfflineDataSourceImpl(
    private val dao: SourcesDao
) : SourcesOfflineDataSource {
    override suspend fun saveSources(sourcesList: List<SourcesItem>) {
        try {
            dao.saveSources(sourcesList)
        } catch (e: Exception) {
            Log.e(TAG, "saveSources: failed to save sources", e)
        }
    }

    override suspend fun getSources(categoryId: String): List<SourcesItem> {
        return try {
            dao.getSources(categoryId)
        } catch (e: Exception) {
            Log.e(TAG, "getSources: failed to get sources", e)
            emptyList()
        }
    }
}