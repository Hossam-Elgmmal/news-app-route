package com.route.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsapp.ui.theme.NewsAppTheme
import com.route.newsapp.utils.AllCards
import com.route.newsapp.utils.DrawerSheet
import com.route.newsapp.utils.NewsAppBar
import com.route.newsapp.utils.NewsTabRow
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

    ModalNavigationDrawer(drawerState = drawerState,
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
        {
            NewsContent(Modifier.padding(top = it.calculateTopPadding()))
        }
    }


}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.image_pattern))
    ) {
        NewsTabRow()
        AllCards()
    }
}
