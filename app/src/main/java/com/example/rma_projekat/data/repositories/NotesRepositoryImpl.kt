package com.example.rma_projekat.data.repositories

import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.data.local.datasources.NotesDao
import com.example.rma_projekat.data.model.NoteEntitiy
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber

class NotesRepositoryImpl(private val notesDao: NotesDao):
    NotesRepository {

    override fun insert(note: Note): Completable {
        val noteEntitiy = NoteEntitiy(note.id,note.title,note.body,note.isArhived)
        return notesDao.insert(noteEntitiy)
    }

    override fun getAll(): Observable<List<Note>> {
        val notesList = notesDao.getAll()
        return notesList.map {
            it.map {
                Note(it.id,it.title,it.body,it.isArhived)
            }
        }
    }

    override fun insertAll(notes: List<Note>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteNote(note: Note): Completable {
        val noteEntitiy = NoteEntitiy(note.id,note.title,note.body,note.isArhived)
        return notesDao.delete(noteEntitiy)

    }

    override fun updateNote(note: Note): Completable {
        val noteEntitiy = NoteEntitiy(note.id,note.title,note.body,note.isArhived)
        return notesDao.update(noteEntitiy)
    }

    override fun getArchived(): Observable<List<Note>> {
        val notesData = getAll()

        return notesData.map {
            it.filter {
                it.isArhived
            }
        }
    }

    override fun getByTitle(title: String): Observable<List<Note>> {
        return notesDao
            .getByTitle(title)
            .map {
                it.map {
                    Note(it.id,it.title,it.body,it.isArhived)
                }
            }
    }
}