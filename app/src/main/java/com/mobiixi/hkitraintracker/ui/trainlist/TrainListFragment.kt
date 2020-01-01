package com.mobiixi.hkitraintracker.ui.trainlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.mobiixi.core.model.Train
import com.mobiixi.hkitraintracker.MyApplication
import com.mobiixi.hkitraintracker.R
import com.mobiixi.hkitraintracker.dagger.app.MyViewModelFactory
import com.mobiixi.hkitraintracker.ui.BaseFragment
import com.mobiixi.hkitraintracker.ui.trainlist.TrainListFragmentArgs.fromBundle
import com.mobiixi.hkitraintracker.ui.trainlist.TrainListFragmentDirections.actionTrainListFragmentToTrainDetailFragment
import javax.inject.Inject

class TrainListFragment : BaseFragment() {

    @BindView(R.id.train_list_rv)
    lateinit var trainsRecyclerView: RecyclerView

    @Inject
    lateinit var viewModelFactory: MyViewModelFactory<TrainListViewModel>

    private val stationCode by lazy {
        arguments?.let { fromBundle(it).searchStationCode } ?: throw IllegalArgumentException("Unexpected argument!")
    }

    lateinit var viewModel: TrainListViewModel

    val clickListener: TrainItemClickListener = this::onTrainSelected
    val trainListViewAdapter = TrainListViewAdapter(clickListener)


    private val searchStationCode by lazy {
        arguments?.let { fromBundle(it).searchStationCode }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_train_list, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainListViewModel::class.java)
        viewModel.start(stationCode)
        viewModel.trains.observe(viewLifecycleOwner, Observer {trainList ->

            trainList.let {
                Log.d("tracker", "fragment observed trains!")
                showTrainList(trainList)
            }
        })
    }

    private fun showTrainList(trainList: List<Train>) {
        Log.d("tracker", "SEARCH STATION CODE: $searchStationCode")
        trainListViewAdapter.refreshTrainList(trainList)
    }

    private fun onTrainSelected(train: Train) {
        Log.d("tracker", "Train: ${train.trainNumber}")
        findNavController().navigate(actionTrainListFragmentToTrainDetailFragment(train))
    }

    private fun setupRecyclerView() {
        trainsRecyclerView.layoutManager = LinearLayoutManager(context)
        trainsRecyclerView.adapter = trainListViewAdapter
    }
}