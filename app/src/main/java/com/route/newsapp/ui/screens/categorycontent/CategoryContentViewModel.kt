package com.route.newsapp.ui.screens.categorycontent

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsapp.api.NewsApi
import com.route.newsapp.contarcts.SourcesRepository
import com.route.newsapp.models.articles.ArticlesItem
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.models.sources.SourceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CategoryContentViewModel"

@HiltViewModel
class CategoryContentViewModel @Inject constructor(
    private val sourcesRepository: SourcesRepository,
    private val newsApi: NewsApi,
) : ViewModel() {


    val newsArticles = mutableStateListOf<ArticlesItem>()
    var loading by mutableStateOf(false)

    var internetAvailable by mutableStateOf(false)

    var selectedIndex by mutableIntStateOf(0)

    val sourcesNamesList = mutableStateListOf<SourceItem>()

    var categoryIndex by mutableIntStateOf(0)

    var isSearchFieldVisible by mutableStateOf(false)

    var myText by mutableStateOf("")
    var searchText by mutableStateOf("")

    fun checkConnection(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities)
        internetAvailable =
            activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
                    || activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
    }

    fun getSourcesNames() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    sourcesRepository.getSources(Constants.categories[categoryIndex])

                if (response.isNotEmpty()) {
                    sourcesNamesList.addAll(response)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getSourcesNames: failed to get sources name", e)
            }
        }
    }

    fun getNewsBySource(sourceIndex: Int = 0) {
        loading = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsApi
                    .getNewsBySource(Constants.API_KEY, sourcesNamesList[sourceIndex].id)
                val articles = response.articles
                if (articles?.isNotEmpty() == true) {
                    newsArticles.clear()
                    newsArticles.addAll(articles)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getNewsBySource: failed to get articles", e)
            }
            loading = false
        }
    }

    fun search() {
        loading = true
        viewModelScope.launch {
            try {
                val response = newsApi
                    .searchEverything(Constants.API_KEY, myText)
                val articles = response.articles
                if (articles?.isNotEmpty() == true) {
                    newsArticles.clear()
                    newsArticles.addAll(articles)
                }
            } catch (e: Exception) {
                Log.e(TAG, "search: failed to search for $myText", e)
            }
            loading = false
        }
    }
}