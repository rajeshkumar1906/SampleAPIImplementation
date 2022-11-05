package com.rajeshkumar.sampleapiimplementation

import android.content.Context
import android.net.ConnectivityManager


class Utlity {
    companion object {

     fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }
}
}