package com.route.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsapp.api.ApiManager
import com.route.newsapp.model.ArticlesItem
import com.route.newsapp.model.ArticlesResponse
import com.route.newsapp.model.Constants
import com.route.newsapp.ui.theme.NewsAppTheme
import com.route.newsapp.utils.AllCards
import com.route.newsapp.utils.DrawerSheet
import com.route.newsapp.utils.NewsAppBar
import com.route.newsapp.utils.NewsTabRow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    val newsArticles = remember {
        mutableStateListOf<ArticlesItem>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.image_pattern),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Spacer(modifier = modifier)
        NewsTabRow { sourceID ->

            ApiManager
                .getNewsServices()
                .getNewsBySource(Constants.API_KEY, sourceID)
                .enqueue(object : Callback<ArticlesResponse> {
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>
                    ) {
                        newsArticles.clear()
                        val articles = response.body()?.articles

                        if (articles?.isNotEmpty() == true) {
                            newsArticles.addAll(articles)
                        }
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {

                    }
                })

        }
        AllCards(newsArticles)
    }
}
