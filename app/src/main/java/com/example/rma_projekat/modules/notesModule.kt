package com.example.rma_projekat.modules

import com.example.rma_projekat.data.local.db.StudentDatabase
import com.example.rma_projekat.data.repositories.NotesRepository
import com.example.rma_projekat.data.repositories.NotesRepositoryImpl
import com.example.rma_projekat.presentation.viewmodel.NotesViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule= module{

    viewModel{
        NotesViewModel(notesRepository = get())
    }
    single<NotesRepository> {
        NotesRepositoryImpl(get())
    }
    single {
        get<StudentDatabase>().getNotesDao()
    }
}