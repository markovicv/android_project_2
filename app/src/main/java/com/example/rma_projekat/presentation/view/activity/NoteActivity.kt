package com.example.rma_projekat.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma_projekat.R
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.presentation.adapter.NotesAdapter
import com.example.rma_projekat.presentation.adapter.SchedulesAdapter
import com.example.rma_projekat.presentation.contracts.NotesContract
import com.example.rma_projekat.presentation.decoration.MarginItemDec
import com.example.rma_projekat.presentation.view.state.DeleteNoteState
import com.example.rma_projekat.presentation.view.state.InsertNoteState
import com.example.rma_projekat.presentation.view.state.NotesState
import com.example.rma_projekat.presentation.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class NoteActivity : AppCompatActivity(R.layout.activity_note) {

    private val notesViewModel:NotesContract.ViewModel by viewModel<NotesViewModel>()
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fabId.setOnClickListener{
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }

        notesRv.layoutManager = LinearLayoutManager(this)
        notesViewModel.deleteDone.observe(this, Observer {
            when(it){
                is DeleteNoteState.Success->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is DeleteNoteState.Error->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
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


        notesAdapter = NotesAdapter({
            notesViewModel.deleteNote(it)
        },{
            val intent = Intent(this,UpdateNoteActivity::class.java)
            intent.putExtra("noteK",it)
            startActivity(intent)
        })
        notesRv.adapter = notesAdapter
        notesViewModel.notesState.observe(this, Observer {
            when(it){
                is NotesState.Succes->{
                    notesAdapter.setNotesList(it.notes)
                }
                is NotesState.Error->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is NotesState.Loading->{
                    Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show()
                }
            }
        })
        notesViewModel.getAllNotes()




    }
}