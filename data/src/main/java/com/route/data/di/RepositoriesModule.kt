package com.route.data.di

import com.route.data.ArticlesRepositoryImpl
import com.route.data.SourcesRepositoryImpl
import com.route.domain.repository.ArticleRepository
import com.route.domain.repository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsSourcesRepository(impl: SourcesRepositoryImpl): SourcesRepository

    @Binds
    abstract fun bindsArticlesRepository(impl: ArticlesRepositoryImpl): ArticleRepository

}