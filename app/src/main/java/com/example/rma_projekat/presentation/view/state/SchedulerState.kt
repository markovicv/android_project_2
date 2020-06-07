package com.example.rma_projekat.presentation.view.state

import com.example.rma_projekat.data.domain.Scheduler

sealed class SchedulerState {
    object Loading:SchedulerState()
    object DataFetched:SchedulerState()
    data class Succes(val schedulers:List<Scheduler>):SchedulerState()
    data class Error(val message:String):SchedulerState()
}