package com.example.rma_projekat.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_projekat.R
import com.example.rma_projekat.data.domain.Note
import kotlinx.android.synthetic.main.activity_update_note.view.*
import kotlinx.android.synthetic.main.notes_item.view.*
import kotlinx.android.synthetic.main.notes_item.view.bodyTv
import kotlinx.android.synthetic.main.notes_item.view.titleTv
import timber.log.Timber

class NotesViewHolder(itemView: View,private val onDeleteBtnClicked:(Int)->Unit,
                      private val onUpdateBtnClicked:(Int)->Unit,
                      private val onArchiveClicked:(Int)->Unit
                      ):RecyclerView.ViewHolder(itemView){

    init {
        itemView.deleteBtn.setOnClickListener {
            onDeleteBtnClicked.invoke(adapterPosition)
        }
        itemView.editBtn.setOnClickListener {
            onUpdateBtnClicked.invoke(adapterPosition)
        }
        itemView.arhive.setOnClickListener {
            onArchiveClicked.invoke(adapterPosition)
        }
    }


    fun bind(note:Note){
        itemView.arhive.setImageResource(if (note.isArhived) { R.drawable.ic_save_green_24dp } else { R.drawable.ic_save_black_24dp })
        itemView.titleTv.setText(note.title)
        itemView.bodyTv.setText(note.body)
    }
}