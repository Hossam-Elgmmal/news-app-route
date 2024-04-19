package com.route.newsapp.di

import com.route.newsapp.api.NewsApi
import com.route.newsapp.contarcts.NetworkHandler
import com.route.newsapp.contarcts.OfflineSources
import com.route.newsapp.contarcts.OnlineSources
import com.route.newsapp.database.NewsDatabase
import com.route.newsapp.datasources.offline.OfflineSourcesImpl
import com.route.newsapp.datasources.online.OnlineSourcesImpl
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
    fun provideOfflineDataSource(database: NewsDatabase): OfflineSources {
        return OfflineSourcesImpl(database.getDao())
    }

    @Singleton
    @Provides
    fun provideOnlineDataSource(
        newsApi: NewsApi
    ): OnlineSources {
        return OnlineSourcesImpl(newsApi)
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