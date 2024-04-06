package com.route.newsapp.di

import com.route.newsapp.contarcts.SourcesRepository
import com.route.newsapp.sourcesrepository.SourcesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsSourcesRepository(impl: SourcesRepositoryImpl): SourcesRepository

}