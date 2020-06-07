package com.example.rma_projekat.presentation.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rma_projekat.R
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.presentation.contracts.NotesContract
import com.example.rma_projekat.presentation.view.state.InsertNoteState
import com.example.rma_projekat.presentation.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteActivity :AppCompatActivity(R.layout.activity_add_note) {

    private val notesViewModel: NotesContract.ViewModel by viewModel<NotesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesViewModel.insertDone.observe(this, Observer {
            when(it){
                is InsertNoteState.Success->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is InsertNoteState.Error->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })


        addBtn.setOnClickListener {
            val title = titleTv.text.toString()
            val body = bodyTv.text.toString()
            if(title.isNullOrBlank() || body.isNullOrBlank()){
                Toast.makeText(this,"Niste sve popunili",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val note = Note(0,title,body,false)
            notesViewModel.insertNote(note)
            finish()
        }
        backBtn.setOnClickListener {
            finish()
        }
    }
}