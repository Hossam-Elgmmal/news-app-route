package com.route.newsapp.models.categories

import com.route.newsapp.R
import com.route.newsapp.ui.theme.Blue
import com.route.newsapp.ui.theme.LightBlue
import com.route.newsapp.ui.theme.Orange
import com.route.newsapp.ui.theme.Pink
import com.route.newsapp.ui.theme.Red
import com.route.newsapp.ui.theme.Yellow

object Constants {

    val categories =
        listOf("sports", "entertainment", "health", "business", "technology", "science")

    val categoriesList = listOf(

        CategoryItem(categories[0], R.drawable.img_sports, R.string.sports, Red),
        CategoryItem(categories[1], R.drawable.img_politics, R.string.entertainment, Blue),
        CategoryItem(categories[2], R.drawable.img_health, R.string.health, Pink),
        CategoryItem(categories[3], R.drawable.img_business, R.string.business, Orange),
        CategoryItem(categories[4], R.drawable.img_environment, R.string.technology, LightBlue),
        CategoryItem(categories[5], R.drawable.img_science, R.string.science, Yellow),

        )
}