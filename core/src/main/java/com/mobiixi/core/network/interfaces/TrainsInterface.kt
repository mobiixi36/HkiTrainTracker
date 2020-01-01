package com.mobiixi.core.network.interfaces

import com.mobiixi.core.model.Train
import com.mobiixi.core.model.TrainsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrainsInterface {
    @GET("live-trains/station/{station_code}")
    suspend fun getTrains(@Path("station_code") stationCode: String,
                          @Query("arrived_trains") nrOfArrivedTrains: Int,
                          @Query("arriving_trains") nrOfArrivingTrains: Int,
                          @Query("departed_trains") nrOfDepartedTrains: Int,
                          @Query("departing_trains") nrOfDepartingTrains: Int,
                          @Query("include_nonstopping") includeNonstopping: Boolean,
                          @Query("train_categories") trainCategory: String,
                          @Query("minutes_before_arrival") minutesBeforeArrival: Int): List<Train>


    // Or return Response<List<Train>> so that we can inspect the status code
}