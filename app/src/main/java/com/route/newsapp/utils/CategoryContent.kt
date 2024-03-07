package com.route.newsapp.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsapp.R
import com.route.newsapp.api.ApiManager
import com.route.newsapp.model.ArticlesItem
import com.route.newsapp.model.ArticlesResponse
import com.route.newsapp.model.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryContent(modifier: Modifier = Modifier) {

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