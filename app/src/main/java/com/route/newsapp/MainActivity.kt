package com.route.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsapp.ui.DrawerSheet
import com.route.newsapp.ui.screens.allcategories.AllCategoriesScreen
import com.route.newsapp.ui.screens.articledetail.ArticleDetailsScreen
import com.route.newsapp.ui.screens.categorycontent.CategoryContentScreen
import com.route.newsapp.ui.screens.settings.SettingsScreen
import com.route.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {

                MainContent()

            }
        }
    }
}

object Destination {
    const val CATEGORIES = "categories"
    const val NEWS = "news"
    const val SETTINGS = "settings"
    const val ARTICLE_DETAILS = "article-details"
}

object Arg {
    const val CATEGORY_INDEX = "category_index"
    const val ARTICLE_TITLE = "article_title"
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8_pro")
@Composable
fun MainContent() {

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(
                onCategoriesClick = {

                    if (navController.currentDestination?.route != Destination.CATEGORIES) {
                        navController.navigate(Destination.CATEGORIES) {
                            popUpTo(Destination.CATEGORIES) {
                                inclusive = true
                            }
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                },
                onSettingsClick = {
                    if (navController.currentDestination?.route != Destination.SETTINGS) {
                        navController.navigate(Destination.SETTINGS) {
                            popUpTo(Destination.CATEGORIES)
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController, startDestination = Destination.CATEGORIES,
            modifier = Modifier
                .paint(
                    painterResource(id = R.drawable.image_pattern),
                    contentScale = ContentScale.FillBounds
                ),
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
                route = Destination.CATEGORIES
            ) {
                AllCategoriesScreen(navController) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable(
                route = "${Destination.NEWS}/{${Arg.CATEGORY_INDEX}}",
                arguments = listOf(navArgument(Arg.CATEGORY_INDEX) {
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt(Arg.CATEGORY_INDEX) ?: 0
                CategoryContentScreen(
                    navController = navController,
                    categoryIndex = index
                ) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable(
                route = Destination.SETTINGS
            ) {
                SettingsScreen {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable(
                route = "${Destination.ARTICLE_DETAILS}/{${Arg.ARTICLE_TITLE}}",
                arguments = listOf(navArgument(Arg.ARTICLE_TITLE) {
                    type = NavType.StringType
                })
            ) {
                val articleTitle = it.arguments?.getString(Arg.ARTICLE_TITLE) ?: ""
                ArticleDetailsScreen(articleTitle = articleTitle)

            }
        }
    }
}