package com.mobiixi.hkitraintracker.ui.trainlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mobiixi.core.model.Train
import com.mobiixi.core.repository.TrainsRepository
import com.mobiixi.hkitraintracker.ui.utils.CoroutineViewModel
import com.mobiixi.hkitraintracker.ui.utils.ScreenRefreshScheduler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainListViewModel @Inject constructor(private val repository: TrainsRepository) : CoroutineViewModel() {

    private val scheduler = ScreenRefreshScheduler(this)
    private val _trains = MutableLiveData<List<Train>>()
    val trains: LiveData<List<Train>>
        get() = _trains

    lateinit var stationCode: String

    fun start(stationCode: String) {
        this.stationCode = stationCode

        var interval: Long = 30 * 1000
        if (stationCode == "HKI") {
            interval = 15 * 1000
        }

        scheduler.start(interval)
    }


    fun loadTrains() {
        viewModelScope.launch (Dispatchers.IO) {
            //live-trains/station/PSL?arrived_trains=0&arriving_trains=5&departed_trains=0&departing_trains=0&include_nonstopping=false&train_categories=Commuter&minutes_before_arrival=10
            val trains = repository.getTrains(stationCode,
                0,
                5,
                0,
                0,
                false,
                "Commuter",
                10)

            _trains.postValue(trains)
        }

    }


}