package com.route.domain.repository

import com.route.domain.models.SourceItemDto

interface SourcesRepository {
    suspend fun getSources(categoryId: String): List<SourceItemDto>
}

interface OnlineSources {
    suspend fun fetchSources(categoryId: String): List<SourceItemDto>
}

interface OfflineSources {
    suspend fun saveSources(sourcesList: List<SourceItemDto>)

    suspend fun getSources(categoryId: String): List<SourceItemDto>
}