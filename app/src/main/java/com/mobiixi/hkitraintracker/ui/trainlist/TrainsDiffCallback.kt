package com.mobiixi.hkitraintracker.ui.trainlist

import androidx.recyclerview.widget.DiffUtil
import com.mobiixi.core.model.Train

class TrainsDiffCallback(private val old: List<Train>,
                         private val new: List<Train>): DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex].trainNumber == new[newIndex].trainNumber
    }

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex] == new[newIndex]
    }
}