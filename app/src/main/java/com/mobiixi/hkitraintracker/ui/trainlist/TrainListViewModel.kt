package com.mobiixi.hkitraintracker.ui.trainlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mobiixi.core.model.Train
import com.mobiixi.core.repository.TrainsRepository
import com.mobiixi.hkitraintracker.ui.utils.CoroutineViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TrainListViewModel @Inject constructor(private val repository: TrainsRepository) : CoroutineViewModel() {

    fun getTrains(stationCode: String): LiveData<List<Train>> {
        // LiveData’s building block already provides a Coroutine Scope
        val trains = liveData(Dispatchers.IO) {
            //live-trains/station/PSL?arrived_trains=0&arriving_trains=5&departed_trains=0&departing_trains=0&include_nonstopping=false&train_categories=Commuter&minutes_before_arrival=10
            val trains = repository.getTrains(stationCode,
                0,
                5,
                0,
                0,
                false,
                "Commuter",
                10)
            emit(trains)
        }

        return trains
    }


}