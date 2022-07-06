package com.mkstudio.FitnessMusicCounter.radio

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.mkstudio.FitnessMusicCounter.util.myLogW
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RadioServiceManager @Inject constructor(private val con: Context) {
    private lateinit var myService : RadioService
    private var isBind = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as RadioService.MyLocalBinder
            myService = binder.getService()
            isBind = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBind = false
        }
    }

    fun playRadio(stationName:String) {
        if ( !isBind ) {
            bindRadio()
        }
        if ( isBind ) {
            myService.playRadio(stationName)
        } else {
            myLogW("radio service is not bind.. can't play")
        }
    }

    fun isBind(): Boolean {
        if ( isBind == false ) {
            myLogW("radio service is not bind")
        }
        return isBind
    }

    fun bindRadio() {
        Intent(con, RadioService::class.java).also { intent ->
            con.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    fun unbindRadio() {
        con.unbindService(connection)
    }
}