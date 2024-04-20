package com.route.newsapp

import android.app.Application
import com.route.data.database.NewsDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NewsDatabase.init(this)
    }
}