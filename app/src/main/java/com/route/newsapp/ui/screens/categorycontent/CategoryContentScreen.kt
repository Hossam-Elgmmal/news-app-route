package com.route.newsapp.ui.screens.categorycontent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsapp.Destination
import com.route.newsapp.ui.theme.Green

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoryContentScreen(
    vm: CategoryContentViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    categoryIndex: Int = 0,
    onNavigationIconClick: () -> Unit = {}
) {

    vm.categoryIndex = categoryIndex

    LaunchedEffect(key1 = Unit) {
        vm.checkConnection()
    }

    Scaffold(
        topBar = {
            CategoryContentAppBar(categoryIndex, onNavigationIconClick) {
                vm.isSearchFieldVisible = true
            }
            AnimatedVisibility(
                visible = vm.isSearchFieldVisible,
                enter = slideInHorizontally(tween(300)) { it } + fadeIn(tween(300)),
                exit = slideOutHorizontally(tween(300)) { it } + fadeOut(tween(300))
            ) {
                SearchBox(onSearch = { text ->
                    vm.searchText = text
                    vm.isSearchFieldVisible = false
                }) { vm.isSearchFieldVisible = false }
            }
        },
        containerColor = Color.Transparent
    ) {

        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()

        ) {

            NewsTabRow(vm)

            if (vm.isInternetAvailable && vm.isLoading) Loading()

            if (vm.myText != vm.searchText.trim()) {

                vm.myText = vm.searchText
                vm.search()
            }

            AllCards(vm.isInternetAvailable, vm.newsArticles) { articleTitle ->
                navController.navigate("${Destination.ARTICLE_DETAILS}/$articleTitle")
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            Modifier.align(Alignment.Center),
            Green,
            trackColor = Color.LightGray
        )

    }
}
