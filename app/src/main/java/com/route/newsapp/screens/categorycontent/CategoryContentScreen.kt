package com.route.newsapp.screens.categorycontent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsapp.R
import com.route.newsapp.ui.theme.Green

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoryContentScreen(
    vm: CategoryContentViewModel = viewModel(),
    searchText: String = "",
    navController: NavHostController = rememberNavController(),
    categoryIndex: Int = 0
) {

    val context = LocalContext.current
    vm.categoryIndex = categoryIndex

    LaunchedEffect(key1 = Unit) {
        vm.checkConnection(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        NewsTabRow(vm)

        if (vm.internetAvailable && vm.loading) Load()

        if (!vm.internetAvailable) NoWifi()

        if (vm.myText != searchText.trim()) {

            vm.myText = searchText
            vm.search()
        }

        AllCards(vm.newsArticles) { articleTitle ->
            navController.navigate("article-details/$articleTitle")
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Load() {
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

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NoWifi() {
    Box(modifier = Modifier.fillMaxSize()) {
        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_no_wifi),
                contentDescription = stringResource(R.string.no_internet),
                tint = Green
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = stringResource(R.string.no_internet), color = Green)
        }
    }
}