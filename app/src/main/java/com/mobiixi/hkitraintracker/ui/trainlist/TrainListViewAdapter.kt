package com.mobiixi.hkitraintracker.ui.trainlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.mobiixi.core.model.Train
import com.mobiixi.hkitraintracker.R

typealias TrainItemClickListener = (Train) -> Unit

class TrainListViewAdapter(private val clickListener: TrainItemClickListener):
                        RecyclerView.Adapter<TrainListViewAdapter.MyViewHolder>() {

    private var trainList = emptyList<Train>()

    class MyViewHolder(itemViewGroup: ViewGroup): RecyclerView.ViewHolder(itemViewGroup) {
        val trainNumber: TextView = itemViewGroup.findViewById(R.id.train_number)
        val lineId: TextView = itemViewGroup.findViewById(R.id.line_id)
    }

    // create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rowView = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.train_row, parent, false) as ViewGroup

        val viewHolder = MyViewHolder(rowView)
        rowView.setOnClickListener {
            clickListener(trainList.get(viewHolder.adapterPosition))
        }

        return viewHolder
    }

    // populate the content of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val train = trainList.get(position)

        holder.trainNumber.text = train.trainNumber.toString()
        holder.lineId.text = train.commuterLineID
    }

    // return the size of train list (invoked by the layout manager)
    override fun getItemCount(): Int = trainList.size

    fun refreshTrainList(trainList: List<Train>) {
        val diffResult = calculateDiff(TrainsDiffCallback(this.trainList, trainList))
        this.trainList = trainList
        diffResult.dispatchUpdatesTo(this)
    }

}