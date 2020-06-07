package com.example.rma_projekat.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NoteEntitiy(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var title:String,
    var body:String,
    var isArhived:Boolean
) {

}