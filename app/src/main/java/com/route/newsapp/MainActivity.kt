package com.route.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsapp.ui.theme.NewsAppTheme
import com.route.newsapp.utils.AllCategoriesGrid
import com.route.newsapp.utils.CategoryContent
import com.route.newsapp.utils.DrawerSheet
import com.route.newsapp.utils.NewsAppBar
import com.route.newsapp.utils.SettingsScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
                NewsAppBar {
                    scope.launch {
                        drawerState.open()
                    }
                }
            })
        { paddingValues ->

            NavHost(
                navController = navController, startDestination = "categories",
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .paint(
                        painterResource(id = R.drawable.image_pattern),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                composable(
                    "categories",
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(durationMillis = 300)
                        ) + fadeIn(animationSpec = tween(durationMillis = 300))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = tween(durationMillis = 300)
                        ) + fadeOut(animationSpec = tween(durationMillis = 300))
                    }
                ) {
                    AllCategoriesGrid(navController)
                }
                composable(
                    "News/{category_index}",
                    arguments = listOf(navArgument("category_index") {
                        type = NavType.IntType
                    }),
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(durationMillis = 300)
                        ) + fadeIn(animationSpec = tween(durationMillis = 300))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { it },
                            animationSpec = tween(durationMillis = 300)
                        ) + fadeOut(animationSpec = tween(durationMillis = 300))
                    }
                ) { navBackStackEntry ->
                    val index = navBackStackEntry.arguments?.getInt("category_index") ?: 0
                    CategoryContent(navController, index)
                }
                composable(
                    "settings",
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
                    SettingsScreen()
                }
            }
        }
    }
}