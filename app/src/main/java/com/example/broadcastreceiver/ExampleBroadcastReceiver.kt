package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast

class ExampleBroadcastReceiver: BroadcastReceiver() {



    override fun onReceive(p0: Context?, p1: Intent?) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(p1?.getAction())){
            if(isNetworkAvailable(p0)){
                Toast.makeText(p0,"Internet Connected",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(p0,"Internet Disconnected",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun isNetworkAvailable(context: Context?): Boolean {
        val connectivityManager: ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // as là ép kiểu dữ liệu
        if(connectivityManager == null){
            return false
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val network: Network? = connectivityManager.activeNetwork
            if(network == null){
                return false
            }
            else{
                val networkCapabilities:NetworkCapabilities? = connectivityManager.getNetworkCapabilities(network)
                return networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }

        }
        else{
              val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
               return networkInfo != null && networkInfo.isConnected
        }
    }


}