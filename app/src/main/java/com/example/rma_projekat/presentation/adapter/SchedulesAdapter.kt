package com.example.rma_projekat.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_projekat.R
import com.example.rma_projekat.data.domain.Scheduler
import com.example.rma_projekat.presentation.diff.SchedulerDiffCallBack
import com.example.rma_projekat.presentation.viewholder.SchedulerViewHolder


class SchedulesAdapter(): RecyclerView.Adapter<SchedulerViewHolder>(){
    private var schedulerList = mutableListOf<Scheduler>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.schedule_item,parent,false)
        return SchedulerViewHolder(containerView)
    }

    override fun getItemCount(): Int {
        return schedulerList.size
    }

    override fun onBindViewHolder(holder: SchedulerViewHolder, position: Int) {
        holder.bind(schedulerList.get(position))
    }
    fun setSchedulerList(newList:List<Scheduler>){
        val diffCallBack = SchedulerDiffCallBack(schedulerList,newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        schedulerList.clear()
        schedulerList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}