package com.example.rma_projekat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rma_projekat.data.domain.Scheduler
import com.example.rma_projekat.data.model.Filter
import com.example.rma_projekat.data.model.Resource
import com.example.rma_projekat.data.repositories.SchedulerRepository
import com.example.rma_projekat.presentation.contracts.SchedulerContract
import com.example.rma_projekat.presentation.view.state.SchedulerState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SchedulerViewModel(private val schedulerRepository: SchedulerRepository):ViewModel(),SchedulerContract.ViewModel{


    override val schedulerState:MutableLiveData<SchedulerState> = MutableLiveData()
    private val subscriptions = CompositeDisposable()

    override fun fetchSchedulers() {
        val subscription = schedulerRepository.fetchScheduler()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it){
                    is Resource.Loading-> schedulerState.value = SchedulerState.Loading
                    is Resource.Succes-> schedulerState.value = SchedulerState.DataFetched
                    is Resource.Error->schedulerState.value = SchedulerState.Error("Error while fetching data")
                }
            },{
                schedulerState.value = SchedulerState.Error("Error while fetching data")

            })
        subscriptions.add(subscription)

    }

    override fun getAllSchedulers() {
        val subscription = schedulerRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                schedulerState.value = SchedulerState.Succes(it)
            },
                {
                    schedulerState.value = SchedulerState.Error("Error while fetching from database")

                })
        subscriptions.add(subscription )
    }

    override fun getFiltered(filter: Filter) {
        val subscription = schedulerRepository
            .filterCourses(filter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                schedulerState.value = SchedulerState.Succes(it)
            },
                {
                    schedulerState.value = SchedulerState.Error("Error while fetching from database")

                })
        subscriptions.add(subscription )


    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()

    }
}