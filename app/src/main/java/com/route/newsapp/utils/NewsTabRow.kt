package com.route.newsapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.route.newsapp.api.ApiManager
import com.route.newsapp.model.Constants
import com.route.newsapp.model.SourcesItem
import com.route.newsapp.model.SourcesResponse
import com.route.newsapp.ui.theme.Green
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsTabRow(onTabSelected: (sourceID: String) -> Unit) {
    val selectedIndex = remember {
        mutableIntStateOf(0)
    }
    val newsList = remember {
        mutableStateListOf<SourcesItem>()
    }

    LaunchedEffect(Unit) {
        ApiManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY, Constants.TECH_CATEGORY)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val sources = response.body()?.sources
                    if (sources?.isNotEmpty() == true) {
                        newsList.addAll(sources)
                    }

                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }

            })
    }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex.intValue,
        divider = { },
        indicator = {},
        edgePadding = 8.dp,
        containerColor = Color.Transparent
    ) {
        newsList.forEachIndexed { index, item ->

            LaunchedEffect(Unit) {
                if (newsList.isNotEmpty())
                    onTabSelected(newsList[0].id ?: "")
            }

            Tab(
                selected = selectedIndex.intValue == index,
                onClick = {
                    onTabSelected(item.id ?: "")
                    selectedIndex.intValue = index
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Green,

                ) {
                Text(
                    text = item.name ?: "",
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