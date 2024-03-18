package com.route.newsapp.screens.articledetail

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.article), color = Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(Green),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
    )
}