package com.example.rcciitapp.observeconnectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged


val Context.connectivityManager
    get(): ConnectivityManager {
        return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

/**
 * Network Utility to observe availability or unavailability of Internet connection
 */
fun ConnectivityManager.observeConnectivityAsFlow() = callbackFlow {
    trySend(currentConnectivityState)

    val callback = networkCallback { connectionState -> trySend(connectionState) }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    registerNetworkCallback(networkRequest, callback)

    awaitClose {
        unregisterNetworkCallback(callback)
    }
}.distinctUntilChanged()

/**
 * Network utility to get current state of internet connection
 */
val ConnectivityManager.currentConnectivityState: ConnectionState
    get() {
        val connected = activeNetwork.let { network ->
            getNetworkCapabilities(network)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        }

        return if (connected) ConnectionState.Available else ConnectionState.Unavailable
    }

fun networkCallback(callback: (ConnectionState) -> Unit): ConnectivityManager.NetworkCallback {
    return object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(ConnectionState.Available)
        }

        override fun onLost(network: Network) {
            callback(ConnectionState.Unavailable)
        }

        override fun onUnavailable() {
            callback(ConnectionState.Unavailable)
        }
    }
}
