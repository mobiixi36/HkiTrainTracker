package com.mobiixi.hkitraintracker.ui.utils

import android.os.CountDownTimer
import android.util.Log
import com.mobiixi.hkitraintracker.ui.trainlist.TrainListViewModel

class ScreenRefreshScheduler(private val viewModel: TrainListViewModel) {

    private lateinit var myTimer: CountDownTimer



    fun start(interval: Long) {
        Log.d("tracker", "interval is $interval")
        viewModel.loadTrains()
        startCountDownTimer(interval)
    }

    private fun startCountDownTimer(interval: Long) {
        myTimer = object: CountDownTimer(interval, interval) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("tracker", "scheduler fired...")

                viewModel.loadTrains()

            }

            override fun onFinish() {
                // start again
                Log.d("tracker", "scheduler onFinish, start again")
                start()
            }
        }

        myTimer.start()
    }
}