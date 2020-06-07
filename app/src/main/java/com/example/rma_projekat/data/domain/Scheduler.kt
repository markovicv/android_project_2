package com.example.rma_projekat.data.domain

data class Scheduler(
    val id:Long,
    val predmet:String,
    val profesor:String,
    val ucionica:String,
    val grupe:String,
    val vreme:String,
    val dan:String
) {
}