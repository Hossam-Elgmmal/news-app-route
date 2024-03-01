package com.route.newsapp.utils

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.route.newsapp.model.NewsData

@Composable
fun AllCards() {
    val newsList = mutableListOf(
        NewsData(),
        NewsData(),
        NewsData()
    )
    LazyColumn {
        items(newsList.size) {
            NewsCard(newsList[it])
        }
    }
}