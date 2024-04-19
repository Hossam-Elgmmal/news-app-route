package com.route.newsapp.datasources.online

import android.util.Log
import com.route.newsapp.api.NewsApi
import com.route.newsapp.contarcts.OnlineSources
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.models.sources.SourceItem

private const val TAG = "SourcesOnlineDataSourceImpl"

class OnlineSourcesImpl(
    private val newsApi: NewsApi
) : OnlineSources {
    override suspend fun fetchSources(categoryId: String): List<SourceItem> {
        return try {
            newsApi
                .getNewsSources(Constants.API_KEY, categoryId)
                .sources

        } catch (e: Exception) {
            Log.e(TAG, "getSourcesNames: failed to get sources name", e)
            emptyList()
        }
    }
}