package com.route.newsapp.screens.categorycontent

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.route.newsapp.api.ApiManager
import com.route.newsapp.models.articles.ArticlesItem
import com.route.newsapp.models.articles.ArticlesResponse
import com.route.newsapp.models.categories.Constants
import com.route.newsapp.models.sources.SourcesItem
import com.route.newsapp.models.sources.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryContentViewModel : ViewModel() {


    val newsArticles = mutableStateListOf<ArticlesItem>()
    var loading by mutableStateOf(false)

    var internetAvailable by mutableStateOf(false)

    var selectedIndex by mutableIntStateOf(0)

    val sourcesNamesList = mutableStateListOf<SourcesItem>()

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
        ApiManager
            .getNewsServices()
            .getNewsSources(Constants.API_KEY, Constants.CATEGORIES_NAMES[categoryIndex])
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val sources = response.body()?.sources
                    if (sources?.isNotEmpty() == true) {
                        sourcesNamesList.addAll(sources)
                    }

                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }

            })
    }

    fun getNewsBySource(sourceIndex: Int = 0) {
        loading = true
        ApiManager
            .getNewsServices()
            .getNewsBySource(Constants.API_KEY, sourcesNamesList[sourceIndex].id ?: "")
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

    fun search() {
        loading = true
        ApiManager
            .getNewsServices()
            .searchEverything(Constants.API_KEY, myText)
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

}