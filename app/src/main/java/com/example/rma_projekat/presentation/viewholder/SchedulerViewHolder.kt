package com.example.rma_projekat.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_projekat.data.domain.Scheduler
import kotlinx.android.synthetic.main.schedule_item.view.*

class SchedulerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    fun bind(scheduler: Scheduler){
        itemView.predmetTv.setText(scheduler.predmet)
        itemView.profesorTv.setText(scheduler.profesor)
        itemView.ucionicaTv.setText(scheduler.ucionica)
    }
}