package com.example.rma_projekat.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SchedulerResponse(val predmet:String,
                        val tip:String,
                        val nastavnik:String,
                        val grupe:String,
                        val dan:String,
                        val termin:String,
                        val ucionica:String


                        ) {
}