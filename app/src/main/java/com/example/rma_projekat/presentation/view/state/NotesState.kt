package com.example.rma_projekat.presentation.view.state

import com.example.rma_projekat.data.domain.Note

sealed class NotesState {
    object Loading:NotesState()
    data class Succes(val notes:List<Note>):NotesState()
    data class Archived(val notes:List<Note>):NotesState()
    data class Error(val message:String):NotesState()
}