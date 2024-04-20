package com.route.data

import com.route.domain.models.SourceItemDto
import com.route.domain.repository.NetworkHandler
import com.route.domain.repository.OfflineSources
import com.route.domain.repository.OnlineSources
import com.route.domain.repository.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val onlineSources: OnlineSources,
    private val offlineSources: OfflineSources,
    private val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSources(categoryId: String): List<SourceItemDto> {
        if (networkHandler.isOnline()) {
            val sourcesList = onlineSources.fetchSources(categoryId)
            offlineSources.saveSources(sourcesList)
            return sourcesList
        }
        return offlineSources.getSources(categoryId)
    }
}