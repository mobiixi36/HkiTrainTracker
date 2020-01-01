package com.mobiixi.core.network.services

import com.mobiixi.core.network.MyRestClient
import com.mobiixi.core.network.interfaces.TrainsInterface
import javax.inject.Inject

class TrainsApiService @Inject constructor(private val restClient: MyRestClient) {

    private val trainsInterface = restClient.getService(TrainsInterface::class.java)

    suspend fun getTrains(stationCode: String,
                  nrOfArrivedTrains: Int,
                  nrOfArrivingTrains: Int,
                  nrOfDepartedTrains: Int,
                  nrOfDepartingTrains: Int,
                  includeNonstopping: Boolean,
                  trainCategories: String,
                  minumtesBeforeArrival: Int) = trainsInterface.getTrains(stationCode,
                                                                            nrOfArrivedTrains,
                                                                            nrOfArrivingTrains,
                                                                            nrOfDepartedTrains,
                                                                            nrOfDepartingTrains,
                                                                            includeNonstopping,
                                                                            trainCategories,
                                                                            minumtesBeforeArrival)



}