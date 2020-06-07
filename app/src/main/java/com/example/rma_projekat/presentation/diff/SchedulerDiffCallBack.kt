package com.example.rma_projekat.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rma_projekat.data.domain.Scheduler

class SchedulerDiffCallBack(val oldSchedulerList:List<Scheduler>, val newSchedulerList:List<Scheduler>):
    DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldSchedulerList.get(oldItemPosition).predmet == newSchedulerList.get(newItemPosition).predmet

    }

    override fun getOldListSize(): Int {
        return oldSchedulerList.size
    }

    override fun getNewListSize(): Int {
        return newSchedulerList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val schedulerOld = oldSchedulerList.get(oldItemPosition)
        val schedulerNew = newSchedulerList.get(newItemPosition)

        return schedulerOld.grupe==schedulerNew.grupe && schedulerOld.profesor == schedulerNew.profesor
                && schedulerOld.ucionica == schedulerNew.ucionica


    }
}