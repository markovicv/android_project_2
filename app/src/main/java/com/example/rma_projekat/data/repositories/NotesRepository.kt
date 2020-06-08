package com.example.rma_projekat.data.repositories

import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.data.model.NoteEntitiy
import io.reactivex.Completable
import io.reactivex.Observable

interface NotesRepository {

    fun insert(note: Note):Completable
    fun getAll():Observable<List<Note>>
    fun insertAll(notes: List<Note>)
    fun deleteNote(note: Note):Completable
    fun updateNote(note:Note):Completable
    fun getByTitleAndBody(title:String):Observable<List<Note>>
    fun setFilter(checked: Boolean)
    fun getRecentNoteCount(): List<Int>
}