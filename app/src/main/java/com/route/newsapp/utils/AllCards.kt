package com.route.newsapp.utils

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.route.newsapp.model.ArticlesItem

@Composable
fun AllCards(articles: List<ArticlesItem>, onCardClick: (String) -> Unit) {

    LazyColumn {
        items(articles.size) { position ->
            NewsCard(articles[position]) {
                articles[position].title?.let { onCardClick(it) }
            }
        }
    }
}