package com.route.newsapp.application

import android.app.Application
import com.route.newsapp.database.NewsDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NewsDatabase.init(this)
    }
}