package com.route.newsapp.ui.screens.categorycontent

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.data.articles.ArticleItem
import com.route.data.sources.SourceItem
import com.route.domain.repository.ArticlesUseCase
import com.route.domain.repository.SourcesUseCase
import com.route.newsapp.models.categories.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CategoryContentViewModel"

@HiltViewModel
class CategoryContentViewModel @Inject constructor(
    private val sourcesUseCase: SourcesUseCase,
    private val articlesUseCase: ArticlesUseCase,
) : ViewModel() {


    val newsArticles = mutableStateListOf<ArticleItem>()
    var isLoading by mutableStateOf(false)

    var selectedIndex by mutableIntStateOf(0)

    val sourcesNamesList = mutableStateListOf<SourceItem>()

    var categoryIndex by mutableIntStateOf(0)

    var isSearchFieldVisible by mutableStateOf(false)

    var myText by mutableStateOf("")
    var searchText by mutableStateOf("")

    var page by mutableIntStateOf(1)
    var sourceIndex by mutableIntStateOf(0)

    fun getNextPage() {
        isLoading = true
        page += 1
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val articles = articlesUseCase
                    .getNextPage(
                        sourcesId = sourcesNamesList[sourceIndex].id,
                        page = page
                    )

                if (articles.isNotEmpty()) {
                    newsArticles.addAll(articles.map {
                        ArticleItem(
                            it.title,
                            it.sourcesId,
                            it.publishedAt,
                            it.author,
                            it.urlToImage,
                            it.description,
                            it.url,
                            it.content
                        )
                    })
                }
            } catch (e: Exception) {
                Log.e(TAG, "getNewsBySource: failed to get articles", e)
            }
            isLoading = false
        }
    }


    fun getSourcesNames() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    sourcesUseCase(Constants.categories[categoryIndex])

                if (response.isNotEmpty()) {
                    sourcesNamesList.addAll(response.map {
                        SourceItem(it.id, it.name, it.description, it.url, it.category)
                    })
                }
            } catch (e: Exception) {
                Log.e(TAG, "getSourcesNames: failed to get sources name", e)
            }
        }
    }

    fun getNewsBySource(sourceIndex: Int = 0) {
        page = 1
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val articles = articlesUseCase(sourcesId = sourcesNamesList[sourceIndex].id)

                if (articles.isNotEmpty()) {
                    newsArticles.clear()
                    newsArticles.addAll(articles.map {
                        ArticleItem(
                            it.title,
                            it.sourcesId,
                            it.publishedAt,
                            it.author,
                            it.urlToImage,
                            it.description,
                            it.url,
                            it.content
                        )
                    })
                }
            } catch (e: Exception) {
                Log.e(TAG, "getNewsBySource: failed to get articles", e)
            }
            isLoading = false
        }
    }

    fun search() {
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val articles = articlesUseCase.search(searchText = myText)
                if (articles.isNotEmpty()) {
                    newsArticles.clear()
                    newsArticles.addAll(articles.map {
                        ArticleItem(
                            it.title,
                            it.sourcesId,
                            it.publishedAt,
                            it.author,
                            it.urlToImage,
                            it.description,
                            it.url,
                            it.content
                        )
                    })

                }
            } catch (e: Exception) {
                Log.e(TAG, "search: failed to search for $myText", e)
            }
            isLoading = false
        }
    }
}