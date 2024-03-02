package com.route.newsapp.utils

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.route.newsapp.model.ArticlesItem

@Composable
fun AllCards(articles: List<ArticlesItem>) {

    LazyColumn {
        items(articles.size) {
            NewsCard(articles[it])
        }
    }
}