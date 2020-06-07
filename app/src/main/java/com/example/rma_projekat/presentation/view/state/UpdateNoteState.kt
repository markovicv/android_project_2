package com.example.rma_projekat.presentation.view.state

sealed class UpdateNoteState {
    data class Success(val message:String):UpdateNoteState()
    data class Error(val message:String):UpdateNoteState()
}