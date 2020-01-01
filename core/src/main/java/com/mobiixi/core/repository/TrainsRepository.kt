package com.mobiixi.core.repository

import com.mobiixi.core.network.services.TrainsApiService
import javax.inject.Inject

class TrainsRepository @Inject constructor() {

    @Inject
    lateinit var trainsApiService: TrainsApiService

    suspend fun getTrains(stationCode: String,
                          nrOfArrivedTrains: Int,
                          nrOfArrivingTrains: Int,
                          nrOfDepartedTrains: Int,
                          nrOfDepartingTrains: Int,
                          includeNonstopping: Boolean,
                          trainCategories: String,
                          minumtesBeforeArrival: Int) = trainsApiService.getTrains(stationCode,
                                                                                    nrOfArrivedTrains,
                                                                                    nrOfArrivingTrains,
                                                                                    nrOfDepartedTrains,
                                                                                    nrOfDepartingTrains,
                                                                                    includeNonstopping,
                                                                                    trainCategories,
                                                                                    minumtesBeforeArrival)



}