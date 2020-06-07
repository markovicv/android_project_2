package com.example.rma_projekat.presentation.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rma_projekat.R
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.presentation.contracts.NotesContract
import com.example.rma_projekat.presentation.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.activity_update_note.*
import kotlinx.android.synthetic.main.activity_update_note.bodyTv
import kotlinx.android.synthetic.main.activity_update_note.titleTv
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateNoteActivity:AppCompatActivity(R.layout.activity_update_note) {
    private val notesViewModel: NotesContract.ViewModel by viewModel<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val note = intent.getParcelableExtra("noteK")as Note
        titleTv.setText(note.title)
        bodyTv.setText(note.body)

        updatebtn.setOnClickListener {
            val title = titleTv.text.toString()
            val body = bodyTv.text.toString()
            if(title.isNullOrBlank() || body.isNullOrBlank()){
                Toast.makeText(this,"Niste sve popunili", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            note.title = title
            note.body = body
            notesViewModel.updateNote(note)
            finish()

        }
        backBtna.setOnClickListener {
            finish()
        }


    }
}