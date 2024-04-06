package com.route.newsapp.di

import com.route.newsapp.api.NewsServices
import com.route.newsapp.contarcts.NetworkHandler
import com.route.newsapp.contarcts.SourcesOfflineDataSource
import com.route.newsapp.contarcts.SourcesOnlineDataSource
import com.route.newsapp.database.NewsDatabase
import com.route.newsapp.datasources.offline.SourcesOfflineDataSourceImpl
import com.route.newsapp.datasources.online.SourcesOnlineDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideOfflineDataSource(database: NewsDatabase): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(database.getDao())
    }

    @Singleton
    @Provides
    fun provideOnlineDataSource(
        newsServices: NewsServices
    ): SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(newsServices)
    }

    @Provides
    fun provideNetworkHandler(): NetworkHandler {
        return object : NetworkHandler {
            override fun isOnline(): Boolean {
                return true
            }
        }
    }
}