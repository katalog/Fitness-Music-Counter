package com.mkstudio.FitnessMusicCounter.radio

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.mkstudio.FitnessMusicCounter.util.Constants
import com.mkstudio.FitnessMusicCounter.util.myLogD

class RadioService : Service() {
    private val myBinder: IBinder = MyLocalBinder()
    private val mRadioMap = mutableMapOf<String, String>()
    private var player : SimpleExoPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    fun playRadio(strStationName:String) {
        val strStationAddr = Constants.radioStationMap[strStationName]
        myLogD("in service : station - $strStationName, $strStationAddr")
        strStationAddr?.let {
            player?.let {
                myLogD("in service : player not null - stop + release")
                player?.stop()
                player?.release()
            }

            // Create a data source factory.
            val dataSourceFactory: DefaultHttpDataSource.Factory = DefaultHttpDataSource.Factory()
            dataSourceFactory.setAllowCrossProtocolRedirects(true)
            dataSourceFactory.setReadTimeoutMs(10000)
            // Create a HLS media source pointing to a playlist uri.
            val hlsMediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(it))
            // Create a player instance.
            player = SimpleExoPlayer.Builder(this).build()
            // Set the media source to be played.
            player?.setMediaSource(hlsMediaSource)

            myLogD("in service : set media source")

            // Prepare the player.
            player?.playWhenReady = true
            player?.prepare()

            myLogD("in service : prefare")

            player?.play()

            if (player == null) {
                myLogD("in service : player null")
            }
        }
    }

    inner class MyLocalBinder: Binder() {
        fun getService() : RadioService = this@RadioService
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
        player?.release()
    }
}