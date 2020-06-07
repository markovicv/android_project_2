package com.example.rma_projekat.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_projekat.data.domain.Note
import kotlinx.android.synthetic.main.activity_update_note.view.*
import kotlinx.android.synthetic.main.notes_item.view.*
import kotlinx.android.synthetic.main.notes_item.view.bodyTv
import kotlinx.android.synthetic.main.notes_item.view.titleTv

class NotesViewHolder(itemView: View,private val onDeleteBtnClicked:(Int)->Unit,
                      private val onUpdateBtnClicked:(Int)->Unit
                      ):RecyclerView.ViewHolder(itemView){

    init {
        itemView.deleteBtn.setOnClickListener {
            onDeleteBtnClicked.invoke(adapterPosition)
        }
        itemView.editBtn.setOnClickListener {
            onUpdateBtnClicked.invoke(adapterPosition)
        }
    }


    fun bind(note:Note){
        itemView.titleTv.setText(note.title)
        itemView.bodyTv.setText(note.body)
    }
}