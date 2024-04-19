package com.route.newsapp.sourcesrepository

import com.route.newsapp.contarcts.NetworkHandler
import com.route.newsapp.contarcts.OfflineSources
import com.route.newsapp.contarcts.OnlineSources
import com.route.newsapp.contarcts.SourcesRepository
import com.route.newsapp.models.sources.SourceItem
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val onlineDataSource: OnlineSources,
    private val offlineDataSource: OfflineSources,
    private val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSources(categoryId: String): List<SourceItem> {
        if (networkHandler.isOnline()) {
            val sourcesList = onlineDataSource.fetchSources(categoryId)
            offlineDataSource.saveSources(sourcesList)
            return sourcesList
        }
        return offlineDataSource.getSources(categoryId)
    }
}