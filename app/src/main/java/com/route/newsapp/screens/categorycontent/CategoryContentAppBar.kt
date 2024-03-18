package com.route.newsapp.screens.categorycontent

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.route.newsapp.R
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryContentAppBar(
    index: Int,
    onNavigationIconClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(Constants.ALL_CATEGORIES[index].titleId),
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Green),
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    painterResource(id = R.drawable.ic_menu),
                    "menu", tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    painterResource(id = R.drawable.ic_search),
                    "search", tint = Color.White
                )
            }
        },
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
    )
}