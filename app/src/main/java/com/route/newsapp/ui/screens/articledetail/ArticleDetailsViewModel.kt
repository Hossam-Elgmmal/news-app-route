package com.route.newsapp.ui.screens.articledetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsapp.api.NewsApi
import com.route.newsapp.models.articles.ArticlesItem
import com.route.newsapp.models.categories.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ArticleDetailsViewModel"

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val newsApi: NewsApi
) : ViewModel() {
    val newsArticles = mutableStateListOf<ArticlesItem>()

    fun getArticle(articleTitle: String) {
        viewModelScope.launch {
            try {
                val response = newsApi
                    .searchEverything(Constants.API_KEY, articleTitle)
                val articles = response.articles
                if (articles?.isNotEmpty() == true) {
                    newsArticles.clear()
                    newsArticles.addAll(articles)
                }
            } catch (e: Exception) {
                Log.e(TAG, "search: failed to search for $articleTitle", e)
            }
        }
    }

    fun openInBrowser(context: Context, url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

}