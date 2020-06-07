package com.example.rma_projekat.presentation.contracts

import androidx.lifecycle.LiveData
import com.example.rma_projekat.data.domain.Scheduler
import com.example.rma_projekat.presentation.view.state.SchedulerState

interface SchedulerContract {
    interface ViewModel{
        val schedulerState:LiveData<SchedulerState>

        fun fetchSchedulers()
        fun getAllSchedulers()

    }
}