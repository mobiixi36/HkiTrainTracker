package com.mobiixi.hkitraintracker.ui.traindetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.mobiixi.hkitraintracker.R
import com.mobiixi.hkitraintracker.ui.BaseFragment
import com.mobiixi.hkitraintracker.ui.traindetail.TrainDetailFragmentArgs.fromBundle

class TrainDetailFragment : BaseFragment() {

    @BindView(R.id.start_station_tv)
    lateinit var startStationTextView: TextView

    private val train by lazy {
        arguments?.let { fromBundle(it).train } ?: throw IllegalArgumentException("Unexpected argument!")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_train_detail, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timetableRows = train.timeTableRows
        Log.d("tracker", "nr of timetablerows: ${timetableRows.size}")

        updateToolbarTitle(train.trainNumber.toString())

        timetableRows[0]?.let {
            startStationTextView.text = it.stationShortCode
        }
    }
}