package com.route.newsapp.models.categories

import com.route.newsapp.BuildConfig
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Blue
import com.route.newsapp.ui.theme.LightBlue
import com.route.newsapp.ui.theme.Orange
import com.route.newsapp.ui.theme.Pink
import com.route.newsapp.ui.theme.Yellow

object Constants {

    const val API_KEY = BuildConfig.API_KEY
    val CATEGORIES_NAMES =
        listOf("sports", "entertainment", "health", "business", "technology", "science")

    val ALL_CATEGORIES = listOf(
        CategoryItem(),
        CategoryItem(CATEGORIES_NAMES[1], R.drawable.img_politics, R.string.entertainment, Blue),
        CategoryItem(CATEGORIES_NAMES[2], R.drawable.img_health, R.string.health, Pink),
        CategoryItem(CATEGORIES_NAMES[3], R.drawable.img_business, R.string.business, Orange),
        CategoryItem(
            CATEGORIES_NAMES[4],
            R.drawable.img_environment,
            R.string.technology,
            LightBlue
        ),
        CategoryItem(CATEGORIES_NAMES[5], R.drawable.img_science, R.string.science, Yellow),
    )
}