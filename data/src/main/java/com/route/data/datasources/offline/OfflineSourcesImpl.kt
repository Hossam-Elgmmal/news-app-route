package com.route.data.datasources.offline

import android.util.Log
import com.route.data.database.SourcesDao
import com.route.data.sources.SourceItem
import com.route.domain.models.SourceItemDto
import com.route.domain.repository.OfflineSources

private const val TAG = "SourcesOfflineDataSourceImpl"

class OfflineSourcesImpl(
    private val dao: SourcesDao
) : OfflineSources {
    override suspend fun saveSources(sourcesList: List<SourceItemDto>) {
        try {
            dao.saveSources(sourcesList.map {
                SourceItem(it.id, it.name, it.description, it.url, it.category)
            })
        } catch (e: Exception) {
            Log.e(TAG, "saveSources: failed to save sources", e)
        }
    }

    override suspend fun getSources(categoryId: String): List<SourceItemDto> {
        return try {
            dao.getSources(categoryId).map { it.toDto() }

        } catch (e: Exception) {
            Log.e(TAG, "getSources: failed to get sources", e)
            emptyList()
        }
    }
}