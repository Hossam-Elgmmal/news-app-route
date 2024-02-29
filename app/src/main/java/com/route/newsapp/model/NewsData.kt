package com.route.newsapp.model

import com.route.newsapp.R

data class NewsData(
    val imageId: Int = R.drawable.img_football,
    val sourceId: Int = R.string.source,
    val titleId: Int = R.string.title,
    val timeId: Int = R.string.time
)