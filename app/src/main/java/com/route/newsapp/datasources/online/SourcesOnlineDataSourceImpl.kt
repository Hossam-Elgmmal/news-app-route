package com.route.newsapp.datasources.online

import android.util.Log
import com.route.newsapp.api.NewsServices
import com.route.newsapp.contarcts.SourcesOnlineDataSource
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.models.sources.SourcesItem

private const val TAG = "SourcesOnlineDataSourceImpl"

class SourcesOnlineDataSourceImpl(
    private val newsServices: NewsServices
) : SourcesOnlineDataSource {
    override suspend fun fetchSources(categoryId: String): List<SourcesItem> {
        return try {
            newsServices
                .getNewsSources(Constants.API_KEY, categoryId)
                .sources

        } catch (e: Exception) {
            Log.e(TAG, "getSourcesNames: failed to get sources name", e)
            emptyList()
        }
    }
}