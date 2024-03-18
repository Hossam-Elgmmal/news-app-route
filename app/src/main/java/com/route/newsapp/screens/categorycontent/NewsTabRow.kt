package com.route.newsapp.screens.categorycontent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsapp.ui.theme.Green

@Composable
fun NewsTabRow(
    vm: CategoryContentViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        vm.getSourcesNames()
    }

    ScrollableTabRow(
        selectedTabIndex = vm.selectedIndex,
        divider = { },
        indicator = {},
        edgePadding = 8.dp,
        containerColor = Color.Transparent
    ) {
        if (vm.sourcesNamesList.isNotEmpty()) {
            LaunchedEffect(key1 = Unit) {
                vm.getNewsBySource()
            }
        }

        vm.sourcesNamesList.forEachIndexed { index, sourcesItem ->

            Tab(
                selected = vm.selectedIndex == index,
                onClick = {
                    vm.getNewsBySource(index)
                    vm.selectedIndex = index
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Green,

                ) {
                Text(
                    text = sourcesItem.name ?: "",
                    modifier = if (vm.selectedIndex == index)
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