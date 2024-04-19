package com.route.newsapp.datasources.offline

import android.util.Log
import com.route.newsapp.contarcts.OfflineSources
import com.route.newsapp.database.SourcesDao
import com.route.newsapp.models.sources.SourceItem

private const val TAG = "SourcesOfflineDataSourceImpl"

class OfflineSourcesImpl(
    private val dao: SourcesDao
) : OfflineSources {
    override suspend fun saveSources(sourcesList: List<SourceItem>) {
        try {
            dao.saveSources(sourcesList)
        } catch (e: Exception) {
            Log.e(TAG, "saveSources: failed to save sources", e)
        }
    }

    override suspend fun getSources(categoryId: String): List<SourceItem> {
        return try {
            dao.getSources(categoryId)
        } catch (e: Exception) {
            Log.e(TAG, "getSources: failed to get sources", e)
            emptyList()
        }
    }
}