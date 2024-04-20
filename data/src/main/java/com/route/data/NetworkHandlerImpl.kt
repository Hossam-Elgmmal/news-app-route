package com.route.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.route.domain.repository.NetworkHandler

class NetworkHandlerImpl(val context: Context) : NetworkHandler {

    override fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities)

        return (activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
                || activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false)
    }
}