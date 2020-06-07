package com.example.rma_projekat.data.remote.datasources

import com.example.rma_projekat.data.model.SchedulerResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface SchedulerDataSourceRemote {

    @GET("json.php")
    fun getAll():Observable<List<SchedulerResponse>>
}