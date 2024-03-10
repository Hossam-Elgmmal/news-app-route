package com.route.newsapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsapp.model.Constants
import com.route.newsapp.ui.theme.NewsAppTheme
import com.route.newsapp.utils.AllCategoriesGrid
import com.route.newsapp.utils.ArticleDetails
import com.route.newsapp.utils.CategoryContent
import com.route.newsapp.utils.DrawerSheet
import com.route.newsapp.utils.NewsAppBar
import com.route.newsapp.utils.SearchBox
import com.route.newsapp.utils.SettingsScreen
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {

                MainContent()

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContent() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var toolbarTitle by remember {
        mutableStateOf("")
    }
    toolbarTitle = stringResource(id = R.string.news_app)
    var isSearchVisible by remember { mutableStateOf(false) }
    var isSearchFieldVisible by remember { mutableStateOf(false) }
    var searchText by remember {
        mutableStateOf("")
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(
                onCategoriesClick = {

                    if (navController.currentDestination?.route != "categories") {
                        navController.navigate("categories") {
                            popUpTo("categories") {
                                inclusive = true
                            }
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                },
                onSettingsClick = {
                    if (navController.currentDestination?.route != "settings") {
                        navController.navigate("settings") {
                            popUpTo("categories")
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {

                NewsAppBar(isSearchVisible, toolbarTitle,
                    onSearchClick = {
                        isSearchFieldVisible = true
                    }
                ) {
                    scope.launch {
                        drawerState.open()
                    }
                }
                AnimatedVisibility(
                    visible = isSearchFieldVisible,
                    enter = slideInHorizontally(tween(300)) { it } + fadeIn(tween(300)),
                    exit = slideOutHorizontally(tween(300)) { it } + fadeOut(tween(300))
                ) {
                    SearchBox(onSearch = { text ->
                        searchText = text
                        isSearchFieldVisible = false
                    }) { isSearchFieldVisible = false }
                }
            }

        ) { paddingValues ->

            NavHost(
                navController = navController, startDestination = "categories",
                modifier = Modifier
                    .paint(
                        painterResource(id = R.drawable.image_pattern),
                        contentScale = ContentScale.FillBounds
                    )
                    .padding(top = paddingValues.calculateTopPadding()),
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(300)
                    ) + fadeOut(tween(300))
                }
            ) {
                composable(
                    "categories"
                ) {
                    toolbarTitle = stringResource(R.string.news_app)
                    isSearchFieldVisible = false
                    isSearchVisible = false
                    AllCategoriesGrid(navController)
                }
                composable(
                    "News/{category_index}",
                    arguments = listOf(navArgument("category_index") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val index = navBackStackEntry.arguments?.getInt("category_index") ?: 0
                    toolbarTitle = stringResource(Constants.ALL_CATEGORIES[index].titleId)
                    isSearchVisible = true
                    isSearchFieldVisible = false
                    CategoryContent(searchText, navController, index)
                }
                composable(
                    "settings"
                ) {
                    toolbarTitle = stringResource(id = R.string.settings)
                    isSearchFieldVisible = false
                    isSearchVisible = false
                    SettingsScreen()
                }
                composable(
                    "article-details/{article-title}",
                    arguments = listOf(navArgument("article-title") {
                        type = NavType.StringType
                    })
                ) {
                    val articleTitle = it.arguments?.getString("article-title") ?: ""
                    toolbarTitle = stringResource(id = R.string.article)
                    isSearchFieldVisible = false
                    isSearchVisible = false
                    ArticleDetails(articleTitle)
                }
            }
        }
    }
}