package com.example.rma_projekat.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
    var id:Long,
    var title:String,
    var body:String,
    var isArhived:Boolean
):Parcelable {
}