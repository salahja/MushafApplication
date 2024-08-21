package com.example.mushafconsolidated.receiversimport

import android.media.MediaPlayer


open interface OnPlayStoppedListener {
    fun onStopped()
    fun onPauseMlayer(mediaPlayer: MediaPlayer?)
}