package com.example.rma_projekat.presentation.contracts

import androidx.lifecycle.LiveData
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.presentation.view.state.*

interface NotesContract {

    interface ViewModel{
        val notesState:LiveData<NotesState>
        val insertDone:LiveData<InsertNoteState>
        val deleteDone:LiveData<DeleteNoteState>
        val updateDone:LiveData<UpdateNoteState>
        val arhivedDone:LiveData<ArhivedState>

        fun getAllNotes()
        fun insertNote(note: Note)
        fun deleteNote(note:Note)
        fun updateNote(note:Note)
        fun getByTitleAndBody(titleBody:String)
        fun setFilter(checked: Boolean)
        fun getRecentNoteCount(): List<Int>
    }
}