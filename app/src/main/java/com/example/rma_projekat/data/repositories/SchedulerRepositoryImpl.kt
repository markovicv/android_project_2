package com.example.rma_projekat.data.repositories

import com.example.rma_projekat.data.remote.datasources.SchedulerDao
import com.example.rma_projekat.data.remote.datasources.SchedulerDataSourceRemote
import com.example.rma_projekat.data.domain.Scheduler
import com.example.rma_projekat.data.model.Resource
import com.example.rma_projekat.data.model.SchedulerEntity
import io.reactivex.Observable

class SchedulerRepositoryImpl(val schedulerDataSourceRemote: SchedulerDataSourceRemote,val schdulerDataSourceLocal:SchedulerDao):
    SchedulerRepository {

    override fun fetchScheduler(): Observable<Resource<Unit>> {
        return schedulerDataSourceRemote.getAll()
            .doOnNext {
                val entities = it.map {
                    SchedulerEntity(0,
                        it.predmet,
                        it.tip,
                        it.nastavnik,
                        it.grupe,
                        it.dan,
                        it.termin,
                        it.ucionica
                        )
                }
                schdulerDataSourceLocal.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Succes(Unit)
            }


    }

    override fun getAll(): Observable<List<Scheduler>> {
        return schdulerDataSourceLocal
            .getAll()
            .map {
                it.map {
                    Scheduler(
                        it.id,
                        it.predmet,
                        it.nastavnik,
                        it.ucionica,
                        it.grupe,
                        it.termin
                    )
                }
            }
    }
}