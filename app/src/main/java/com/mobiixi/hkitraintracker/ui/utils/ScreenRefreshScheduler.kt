package com.mobiixi.hkitraintracker.ui.utils

import android.os.CountDownTimer
import android.util.Log

class ScreenRefreshScheduler(private val interval: Long) {
    private val myTimer = object: CountDownTimer(interval, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.d("track", "scheduler fired...")
        }

        override fun onFinish() {
            start()
        }
    }

    fun start() {
        myTimer.start()
    }
}