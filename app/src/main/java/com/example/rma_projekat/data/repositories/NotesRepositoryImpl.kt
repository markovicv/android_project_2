package com.example.rma_projekat.data.repositories

import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.data.local.datasources.NotesDao
import com.example.rma_projekat.data.model.NoteEntitiy
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class NotesRepositoryImpl(private val notesDao: NotesDao):
    NotesRepository {

    var archiveFilter = false;

    override fun insert(note: Note): Completable {
        val noteEntitiy = NoteEntitiy(note.id,note.title,note.body,note.isArhived)
        return notesDao.insert(noteEntitiy)
    }

    override fun getAll(): Observable<List<Note>> {
        val notesList = notesDao.getAll()
        return notesList.map {
            it.map {
                Note(it.id,it.title,it.body,it.isArhived)
            }.filter {
                archiveFilter || !it.isArhived
            }
        }
    }

    override fun insertAll(notes: List<Note>) {

    }

    override fun deleteNote(note: Note): Completable {
        val noteEntitiy = NoteEntitiy(note.id,note.title,note.body,note.isArhived)
        return notesDao.delete(noteEntitiy)
    }

    override fun updateNote(note: Note): Completable {
        val noteEntitiy = NoteEntitiy(note.id,note.title,note.body,note.isArhived)
        return notesDao.update(noteEntitiy)
    }

    override fun getByTitleAndBody(titleBody: String): Observable<List<Note>> {
        val rawNotes = getAll()
        return rawNotes.map {
            it.filter {
                it.title.contains(titleBody,true) || it.body.contains(titleBody,true)
            }
        }
    }

    override fun getRecentNoteCount(): List<Int> {

//        val rawNotes = getAll()
//        return listOf(
//            rawNotes.map { it.filter { daysDiff(it.created) == 0 } }.count()
//        )

        return listOf()
    }

    private fun daysDiff(date: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val now = Date()
        val then = sdf.parse(date)

        return TimeUnit.DAYS.convert((now.time - then.time), TimeUnit.MILLISECONDS)
    }

    override fun setFilter(checked: Boolean) {
        archiveFilter = checked
    }

}