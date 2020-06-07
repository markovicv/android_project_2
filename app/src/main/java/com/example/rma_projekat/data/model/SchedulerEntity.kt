package com.example.rma_projekat.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedulers")
class SchedulerEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    val predmet:String,
    val tip:String,
    val nastavnik:String,
    val grupe:String,
    val dan:String,
    val termin:String,
    val ucionica:String

) {
}