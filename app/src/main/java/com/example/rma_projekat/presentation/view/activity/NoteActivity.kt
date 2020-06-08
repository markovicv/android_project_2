package com.example.rma_projekat.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma_projekat.R
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.presentation.Konstants
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

        searchNotesId.doAfterTextChanged {
            notesViewModel.getByTitleAndBody(it.toString())
        }

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

        notesAdapter = NotesAdapter({
            notesViewModel.deleteNote(it)
        },{
            val intent = Intent(this,UpdateNoteActivity::class.java)
            intent.putExtra(Konstants.UPDATE_NOTE,it)
            startActivity(intent)
        },{
            if(it.isArhived){
                it.isArhived=false
                notesViewModel.updateNote(it)
                Toast.makeText(this,"Nije vise arhiviran",Toast.LENGTH_SHORT).show()
            }
            else{
                it.isArhived = true
                notesViewModel.updateNote(it)
                Toast.makeText(this,"uspesno arhiviran",Toast.LENGTH_SHORT).show()
            }

        })
        archivedSwitch.setOnCheckedChangeListener { _, isChecked ->
            notesViewModel.setFilter(isChecked)
            notesViewModel.getAllNotes()
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.scheduler_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.schedulerMenu){
            val intent = Intent(this,SchedulerActivity::class.java)
            startActivity(intent)
            finish()
        } else if (item.itemId == R.id.statMenuId) {
            val intent = Intent(this,StatisticsActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}