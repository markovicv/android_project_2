package com.example.rma_projekat.presentation.view.state

sealed class ArhivedState {
    data class Success(val message:String):ArhivedState()
    data class Error(val message:String):ArhivedState()
}