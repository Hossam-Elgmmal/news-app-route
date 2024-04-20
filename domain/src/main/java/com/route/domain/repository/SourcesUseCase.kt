package com.route.domain.repository

import com.route.domain.models.SourceItemDto
import javax.inject.Inject

class SourcesUseCase @Inject constructor(
    private val repository: SourcesRepository
) {
    suspend operator fun invoke(categoryId: String): List<SourceItemDto> {
        return repository.getSources(categoryId = categoryId)
    }
}