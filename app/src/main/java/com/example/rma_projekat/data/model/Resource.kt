package com.example.rma_projekat.data.model

sealed class Resource<out T>{
    data class Succes<out T>(val data:T):Resource<T>()
    data class Loading<out T>(val message:String=""):Resource<T>()
    data class Error<out T>(val error:Throwable=Throwable(),val data:T?=null):Resource<T>()
}