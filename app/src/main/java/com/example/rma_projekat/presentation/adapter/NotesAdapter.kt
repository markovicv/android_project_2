package com.example.rma_projekat.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_projekat.R
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.presentation.diff.NotesDiffCallBack
import com.example.rma_projekat.presentation.diff.SchedulerDiffCallBack
import com.example.rma_projekat.presentation.viewholder.NotesViewHolder
import com.example.rma_projekat.presentation.viewholder.SchedulerViewHolder

class NotesAdapter(private val onDeleteBtnClicked:(Note)->Unit,
                   private val onUpdateBtnClicked:(Note)->Unit
                   ):RecyclerView.Adapter<NotesViewHolder>() {
    private var notesList = mutableListOf<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.notes_item,parent,false)
        return NotesViewHolder(containerView,{
            val note = notesList.get(it)
            onDeleteBtnClicked.invoke(note)
        },{
            val note = notesList.get(it)
            onUpdateBtnClicked.invoke(note)
        })
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesList.get(position))
    }
    fun setNotesList(newList:List<Note>){
        val diffCallBack = NotesDiffCallBack(notesList,newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        notesList.clear()
        notesList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}