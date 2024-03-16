package com.route.newsapp.models.categories

import androidx.compose.ui.graphics.Color
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Red

data class CategoryItem(
    val apiId: String = "sport",
    val imageId: Int = R.drawable.img_sports,
    val titleId: Int = R.string.sports,
    val color: Color = Red
)
