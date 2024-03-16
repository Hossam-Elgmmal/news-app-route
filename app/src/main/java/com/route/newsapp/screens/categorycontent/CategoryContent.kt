package com.route.newsapp.screens.categorycontent

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsapp.R
import com.route.newsapp.api.ApiManager
import com.route.newsapp.models.articles.ArticlesItem
import com.route.newsapp.models.articles.ArticlesResponse
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.ui.theme.Green
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoryContent(
    searchText: String = "",
    navController: NavHostController = rememberNavController(),
    categoryIndex: Int = 0
) {

    val newsArticles = remember { mutableStateListOf<ArticlesItem>() }
    var loading by remember { mutableStateOf(true) }
    var myText by remember { mutableStateOf("") }

    var internetAvailable by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork
    val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities)
    internetAvailable = activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
            || activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        NewsTabRow(categoryIndex) { sourceID ->

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
                        loading = false
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        loading = false

                    }
                })

        }

        if (internetAvailable && loading) {

            Load(Modifier.fillMaxSize())
        }
        if (!internetAvailable) {
            NoWifi()
        }
        if (myText != searchText.trim()) {
            myText = searchText

            ApiManager.getNewsServices().searchEverything(Constants.API_KEY, myText)
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
                        loading = false

                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        loading = false
                    }
                })

        }

        AllCards(newsArticles) { articleTitle ->
            navController.navigate("article-details/$articleTitle")
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Load(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
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
fun NoWifi(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
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