package com.route.newsapp.activities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var toolbarTitle by mutableStateOf("")
    var isSearchVisible by mutableStateOf(false)
    var isSearchFieldVisible by mutableStateOf(false)
    var searchText by mutableStateOf("")

}