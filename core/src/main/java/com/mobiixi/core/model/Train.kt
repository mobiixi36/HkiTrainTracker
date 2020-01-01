package com.mobiixi.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Train(val trainNumber: Int,
                 val commuterLineID: String,
                 val timeTableRows: List<Timetable>) : Parcelable