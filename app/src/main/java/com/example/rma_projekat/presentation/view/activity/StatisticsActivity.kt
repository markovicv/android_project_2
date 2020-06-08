package com.example.rma_projekat.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.rma_projekat.R
import com.example.rma_projekat.presentation.contracts.NotesContract
import com.example.rma_projekat.presentation.viewmodel.NotesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsActivity : AppCompatActivity(R.layout.activity_statistics) {

    private val notesViewModel: NotesContract.ViewModel by viewModel<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        notesViewModel.getRecentNoteCount()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.scheduler_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.notesMenuId){
            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if(item.itemId == R.id.schedulerMenu){
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