package com.example.rcciitapp.observeconnectivity

import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object DoesNetworkHasInternet {
    fun execute(): Boolean {
        return try {
            Log.d("PING", "PINGING GOOGLE")
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d("PING", "PING Success.")
            true
        } catch (e: IOException) {
            Log.e("PING", "No Internet. ${e}")
            false
        }
    }
}