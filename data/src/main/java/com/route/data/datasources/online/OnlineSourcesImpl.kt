package com.route.data.datasources.online

import android.util.Log
import com.route.data.api.NewsApi
import com.route.domain.models.SourceItemDto
import com.route.domain.repository.OnlineSources

private const val TAG = "SourcesOnlineDataSourceImpl"

class OnlineSourcesImpl(
    private val newsApi: NewsApi
) : OnlineSources {
    override suspend fun fetchSources(categoryId: String): List<SourceItemDto> {
        return try {
            newsApi
                .getNewsSources(category = categoryId)
                .sources.map {
                    it.toDto()
                }

        } catch (e: Exception) {
            Log.e(TAG, "getSourcesNames: failed to get sources name", e)
            emptyList()
        }
    }
}