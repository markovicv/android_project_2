package com.example.rma_projekat.modules

import com.example.rma_projekat.data.local.db.StudentDatabase
import com.example.rma_projekat.data.remote.datasources.SchedulerDataSourceRemote
import com.example.rma_projekat.data.repositories.SchedulerRepository
import com.example.rma_projekat.data.repositories.SchedulerRepositoryImpl
import com.example.rma_projekat.presentation.viewmodel.SchedulerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val schedulerModul = module {
    viewModel {
        SchedulerViewModel(get())
    }

    single<SchedulerRepository>{
        SchedulerRepositoryImpl(
            schedulerDataSourceRemote = get(), schdulerDataSourceLocal = get()
        )
    }
    single {
        get<StudentDatabase>().getSchedulerDao()
    }
    single<SchedulerDataSourceRemote> {
        create(get())
    }
}