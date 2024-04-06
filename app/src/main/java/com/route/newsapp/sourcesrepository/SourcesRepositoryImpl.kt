package com.route.newsapp.sourcesrepository

import com.route.newsapp.contarcts.NetworkHandler
import com.route.newsapp.contarcts.SourcesOfflineDataSource
import com.route.newsapp.contarcts.SourcesOnlineDataSource
import com.route.newsapp.contarcts.SourcesRepository
import com.route.newsapp.models.sources.SourcesItem
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val onlineDataSource: SourcesOnlineDataSource,
    private val offlineDataSource: SourcesOfflineDataSource,
    private val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSources(categoryId: String): List<SourcesItem> {
        if (networkHandler.isOnline()) {
            val sourcesList = onlineDataSource.fetchSources(categoryId)
            offlineDataSource.saveSources(sourcesList)
            return sourcesList
        }
        return offlineDataSource.getSources(categoryId)
    }
}