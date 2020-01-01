package com.mobiixi.hkitraintracker.ui.userinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import butterknife.BindView
import butterknife.ButterKnife
import com.mobiixi.hkitraintracker.R
import com.mobiixi.hkitraintracker.ui.BaseFragment
import com.mobiixi.hkitraintracker.ui.userinput.UserInputFragmentDirections.actionUserInputFragmentToTrainListFragment

class UserInputFragment : BaseFragment() {
    @BindView(R.id.search_btn)
    lateinit var searchBtn: Button

    @BindView(R.id.station_code_et)
    lateinit var stationCodeEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_input, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBtn.setOnClickListener { view ->
            val station = stationCodeEditText.text.toString()
            if (station.trim().isNotBlank()) {
                findNavController().navigate(actionUserInputFragmentToTrainListFragment(station.trim()))
            }
        }
    }

}