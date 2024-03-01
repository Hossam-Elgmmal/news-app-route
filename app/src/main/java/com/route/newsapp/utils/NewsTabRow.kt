package com.route.newsapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.route.newsapp.ui.theme.Green

@Composable
fun NewsTabRow() {
    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    val newsList = listOf(
        "ABC News",
        "ABC News",
        "ABC News",
        "ABC News",
    )
    ScrollableTabRow(
        selectedTabIndex = selectedIndex.intValue,
        divider = { },
        indicator = {},
        edgePadding = 8.dp
    ) {
        newsList.forEachIndexed { index, item ->

            Tab(
                selected = selectedIndex.intValue == index,
                onClick = {
                    selectedIndex.intValue = index
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Green,

                ) {
                Text(
                    text = item,
                    modifier = if (selectedIndex.intValue == index)
                        Modifier
                            .padding(8.dp, 16.dp)
                            .background(Green, CircleShape)
                            .padding(16.dp, 8.dp)
                    else
                        Modifier
                            .padding(8.dp, 16.dp)
                            .border(2.dp, Green, CircleShape)
                            .background(Color.White, CircleShape)
                            .padding(16.dp, 8.dp)
                )
            }
        }
    }
}