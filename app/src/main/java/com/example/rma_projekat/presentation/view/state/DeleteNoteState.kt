package com.example.rma_projekat.presentation.view.state

sealed class DeleteNoteState {
    data class Success(val message:String):DeleteNoteState()
    data class Error(val message:String):DeleteNoteState()
}