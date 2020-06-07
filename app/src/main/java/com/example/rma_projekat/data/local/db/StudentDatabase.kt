package com.example.rma_projekat.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rma_projekat.data.local.datasources.NotesDao
import com.example.rma_projekat.data.model.NoteEntitiy
import com.example.rma_projekat.data.remote.datasources.SchedulerDao
import com.example.rma_projekat.data.model.SchedulerEntity

@Database(
    entities = [NoteEntitiy::class, SchedulerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StudentDatabase:RoomDatabase() {
    abstract fun getNotesDao():NotesDao
    abstract fun getSchedulerDao():SchedulerDao
}