package com.route.data.di

import android.content.Context
import com.route.data.NetworkHandlerImpl
import com.route.data.api.NewsApi
import com.route.data.database.NewsDatabase
import com.route.data.datasources.offline.OfflineArticlesImpl
import com.route.data.datasources.offline.OfflineSourcesImpl
import com.route.data.datasources.online.OnlineArticlesImpl
import com.route.data.datasources.online.OnlineSourcesImpl
import com.route.domain.repository.NetworkHandler
import com.route.domain.repository.OfflineArticles
import com.route.domain.repository.OfflineSources
import com.route.domain.repository.OnlineArticles
import com.route.domain.repository.OnlineSources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideOfflineSources(database: NewsDatabase): OfflineSources {
        return OfflineSourcesImpl(database.getSourcesDao())
    }

    @Singleton
    @Provides
    fun provideOnlineSources(
        newsApi: NewsApi
    ): OnlineSources {
        return OnlineSourcesImpl(newsApi)
    }

    @Singleton
    @Provides
    fun provideNetworkHandler(
        @ApplicationContext context: Context
    ): NetworkHandler {
        return NetworkHandlerImpl(context)
    }

    @Singleton
    @Provides
    fun provideOnlineArticles(
        newsApi: NewsApi
    ): OnlineArticles {
        return OnlineArticlesImpl(newsApi)
    }

    @Singleton
    @Provides
    fun provideOfflineArticles(
        database: NewsDatabase
    ): OfflineArticles {
        return OfflineArticlesImpl(database.getArticlesDao())
    }
}