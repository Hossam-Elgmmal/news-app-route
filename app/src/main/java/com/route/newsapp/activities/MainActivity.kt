package com.route.newsapp.activities

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsapp.R
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.screens.allcategories.AllCategoriesScreen
import com.route.newsapp.screens.articledetail.ArticleDetailsScreen
import com.route.newsapp.screens.categorycontent.CategoryContentScreen
import com.route.newsapp.screens.settings.SettingsScreen
import com.route.newsapp.ui.theme.NewsAppTheme
import com.route.newsapp.utils.DrawerSheet
import com.route.newsapp.utils.NewsAppBar
import com.route.newsapp.utils.SearchBox
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

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun MainContent(vm: MainActivityViewModel = viewModel()) {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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

                NewsAppBar(
                    vm.isSearchVisible, vm.toolbarTitle,
                    onSearchClick = {
                        vm.isSearchFieldVisible = true
                    }
                ) {
                    scope.launch {
                        drawerState.open()
                    }
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
                    vm.toolbarTitle = stringResource(R.string.news_app)
                    vm.isSearchFieldVisible = false
                    vm.isSearchVisible = false
                    AllCategoriesScreen(navController)
                }
                composable(
                    "News/{category_index}",
                    arguments = listOf(navArgument("category_index") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val index = navBackStackEntry.arguments?.getInt("category_index") ?: 0
                    vm.toolbarTitle = stringResource(Constants.ALL_CATEGORIES[index].titleId)
                    vm.isSearchVisible = true
                    vm.isSearchFieldVisible = false
                    CategoryContentScreen(
                        searchText = vm.searchText,
                        navController = navController,
                        categoryIndex = index
                    )
                }
                composable(
                    "settings"
                ) {
                    vm.toolbarTitle = stringResource(id = R.string.settings)
                    vm.isSearchFieldVisible = false
                    vm.isSearchVisible = false
                    SettingsScreen()
                }
                composable(
                    "article-details/{article-title}",
                    arguments = listOf(navArgument("article-title") {
                        type = NavType.StringType
                    })
                ) {
                    val articleTitle = it.arguments?.getString("article-title") ?: ""
                    vm.toolbarTitle = stringResource(id = R.string.article)
                    vm.isSearchFieldVisible = false
                    vm.isSearchVisible = false
                    ArticleDetailsScreen(articleTitle = articleTitle)
                }
            }
        }
    }
}