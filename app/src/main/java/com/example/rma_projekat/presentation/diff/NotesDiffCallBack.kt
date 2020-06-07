package com.example.rma_projekat.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rma_projekat.data.domain.Note

class NotesDiffCallBack(val oldList:List<Note>,val newList:List<Note>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).id==newList.get(newItemPosition).id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val noteOld = oldList.get(oldItemPosition)
        val noteNew = newList.get(newItemPosition)

        return noteNew.body == noteOld.body && noteNew.title == noteOld.title
                && noteNew.isArhived == noteOld.isArhived
    }
}