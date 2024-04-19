package com.route.newsapp.contarcts

import com.route.newsapp.models.sources.SourceItem

interface SourcesRepository {
    suspend fun getSources(categoryId: String): List<SourceItem>
}

interface OnlineSources {
    suspend fun fetchSources(categoryId: String): List<SourceItem>
}

interface OfflineSources {
    suspend fun saveSources(sourcesList: List<SourceItem>)

    suspend fun getSources(categoryId: String): List<SourceItem>
}