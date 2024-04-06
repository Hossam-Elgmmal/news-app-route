package com.route.newsapp.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import com.route.newsapp.R
import com.route.newsapp.screens.allcategories.AllCategoriesScreen
import com.route.newsapp.screens.articledetail.ArticleDetailsScreen
import com.route.newsapp.screens.categorycontent.CategoryContentScreen
import com.route.newsapp.screens.settings.SettingsScreen
import com.route.newsapp.ui.theme.NewsAppTheme
import com.route.newsapp.utils.DrawerSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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
        NavHost(
            navController = navController, startDestination = "categories",
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
                "categories"
            ) {
                AllCategoriesScreen(navController) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable(
                "News/{category_index}",
                arguments = listOf(navArgument("category_index") {
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("category_index") ?: 0
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
                "settings"
            ) {
                SettingsScreen {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable(
                "article-details/{article-title}",
                arguments = listOf(navArgument("article-title") {
                    type = NavType.StringType
                })
            ) {
                val articleTitle = it.arguments?.getString("article-title") ?: ""
                ArticleDetailsScreen(articleTitle = articleTitle)

            }
        }
    }
}