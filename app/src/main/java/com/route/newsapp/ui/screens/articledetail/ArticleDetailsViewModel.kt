package com.route.newsapp.ui.screens.articledetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.articles.ArticleItem
import com.route.domain.repository.ArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ArticleDetailsViewModel"

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val articlesUseCase: ArticlesUseCase
) : ViewModel() {
    var newsArticle by mutableStateOf(ArticleItem())

    fun getArticle(articleTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val article = articlesUseCase.getArticleDetails(title = articleTitle)
                newsArticle = ArticleItem(
                    article.title,
                    article.sourcesId,
                    article.publishedAt,
                    article.author,
                    article.urlToImage,
                    article.description,
                    article.url,
                    article.content
                )

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