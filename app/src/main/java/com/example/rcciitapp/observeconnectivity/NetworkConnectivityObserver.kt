package com.example.rcciitapp.observeconnectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetworkConnectivityObserver(private val context: Context) : ConnectivityObserver {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    /*val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                    val hasInternetNetworkCapabilities =
                        networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
                    if (hasInternetNetworkCapabilities == true) {
                        CoroutineScope(Dispatchers.IO).launch {
                            //trySend(ConnectivityObserver.Status.Available)

                            val hasInternet = DoesNetworkHasInternet.execute()
                            if (hasInternet) {
                                withContext(Dispatchers.Main) {
                                    launch { trySend(ConnectivityObserver.Status.Available) }
                                }
                            } else {
                                launch { trySend(ConnectivityObserver.Status.Lost) }
                            }


                        }
                    }*/
                    trySend(ConnectivityObserver.Status.Available)

                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { trySend(ConnectivityObserver.Status.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { trySend(ConnectivityObserver.Status.Lost) }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }.distinctUntilChanged()
    }

}

