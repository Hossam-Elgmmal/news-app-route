package com.route.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet()
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
            val navController = rememberNavController()
            NavHost(
                navController = navController, startDestination = "categories",
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
            ) {
                composable("categories") {
                    AllCategoriesGrid(navController)
                }
                composable(
                    "News/{category_index}",
                    arguments = listOf(navArgument("category_index") {
                        type = NavType.IntType
                    })
                ) { navBackStackEntry ->
                    val index = navBackStackEntry.arguments?.getInt("category_index") ?: 0
                    CategoryContent(navController, index)
                }
            }
        }
    }
}