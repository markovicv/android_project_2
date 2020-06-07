package com.example.rma_projekat.data.local.datasources

import androidx.room.*
import com.example.rma_projekat.data.model.NoteEntitiy
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(notesEntitiy: NoteEntitiy):Completable

    @Query("SELECT * FROM notes")
    abstract fun getAll():Observable<List<NoteEntitiy>>

    //abstract fun insertAll(entities:List<NoteEntitiy>):Completable

    @Delete
    abstract fun delete(entitiy: NoteEntitiy):Completable

    @Update
    abstract fun update(entitiy: NoteEntitiy):Completable
}