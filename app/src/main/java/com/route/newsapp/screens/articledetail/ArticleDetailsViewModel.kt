package com.route.newsapp.screens.articledetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.route.newsapp.api.ApiManager
import com.route.newsapp.models.articles.ArticlesItem
import com.route.newsapp.models.articles.ArticlesResponse
import com.route.newsapp.models.categories.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleDetailsViewModel : ViewModel() {
    val newsArticles = mutableStateListOf<ArticlesItem>()

    fun getArticle(articleTitle: String) {

        ApiManager.getNewsServices().searchEverything(Constants.API_KEY, articleTitle)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    val articles = response.body()?.articles
                    articles?.let { newsArticles.addAll(it) }
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {

                }
            })
    }

    fun openInBrowser(context: Context, url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

}