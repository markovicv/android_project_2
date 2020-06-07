package com.example.rma_projekat.data.repositories

import com.example.rma_projekat.data.domain.Scheduler
import com.example.rma_projekat.data.model.Filter
import com.example.rma_projekat.data.model.Resource
import io.reactivex.Observable

interface SchedulerRepository {
    fun fetchScheduler():Observable<Resource<Unit>>
    fun getAll():Observable<List<Scheduler>>
    fun filterCourses(filter: Filter): Observable<List<Scheduler>>
}