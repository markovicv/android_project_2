package com.example.rma_projekat.presentation.contracts

import androidx.lifecycle.LiveData
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.presentation.view.state.DeleteNoteState
import com.example.rma_projekat.presentation.view.state.InsertNoteState
import com.example.rma_projekat.presentation.view.state.NotesState
import com.example.rma_projekat.presentation.view.state.UpdateNoteState

interface NotesContract {

    interface ViewModel{
        val notesState:LiveData<NotesState>
        val insertDone:LiveData<InsertNoteState>
        val deleteDone:LiveData<DeleteNoteState>
        val updateDone:LiveData<UpdateNoteState>

        fun getAllNotes()
        fun insertNote(note: Note)
        fun deleteNote(note:Note)
        fun updateNote(note:Note)
    }
}