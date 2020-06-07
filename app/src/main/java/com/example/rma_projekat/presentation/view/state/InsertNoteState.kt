package com.example.rma_projekat.presentation.view.state

sealed class InsertNoteState {
    data class Success(val message:String):InsertNoteState()
    data class Error(val message:String):InsertNoteState()
}