package com.example.rma_projekat.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rma_projekat.data.domain.Note
import com.example.rma_projekat.data.model.Resource
import com.example.rma_projekat.data.repositories.NotesRepository
import com.example.rma_projekat.presentation.contracts.NotesContract
import com.example.rma_projekat.presentation.view.state.*
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NotesViewModel(private val notesRepository: NotesRepository):ViewModel(),NotesContract.ViewModel {

    override val notesState: MutableLiveData<NotesState> = MutableLiveData()
    override val insertDone: MutableLiveData<InsertNoteState> = MutableLiveData()
    override val deleteDone: MutableLiveData<DeleteNoteState> = MutableLiveData()
    private val subsricptions = CompositeDisposable()
    override val updateDone: MutableLiveData<UpdateNoteState> = MutableLiveData()
    override val arhivedDone: MutableLiveData<ArhivedState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                notesRepository
                    .getByTitle(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    notesState.value = NotesState.Succes(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subsricptions.add(subscription)
    }

    override fun getAllNotes() {
        val subscription = notesRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                notesState.value = NotesState.Succes(it)
            },{
                notesState.value = NotesState.Error("Error while fetching notes from db")
            })
        subsricptions.add(subscription)

    }

    override fun insertNote(note: Note) {
        val subscription = notesRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertDone.value = InsertNoteState.Success("Uspesno dodat u bazu")
            },{
                insertDone.value = InsertNoteState.Error("error while inserting new note")
            })
        subsricptions.add(subscription)
    }

    override fun deleteNote(note: Note) {
        val subscription = notesRepository
            .deleteNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deleteDone.value = DeleteNoteState.Success("Uspedno izbrisan iz baze")
            },{
                deleteDone.value = DeleteNoteState.Error("Niste uspesno izbrisali ")
            })

        subsricptions.add(subscription)

    }

    override fun updateNote(note: Note) {
        val subscription = notesRepository
            .updateNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateDone.value = UpdateNoteState.Success("Uspesno updateovan")
            },{
                updateDone.value = UpdateNoteState.Error("Niste uspesno updateovali")
            })
        subsricptions.add(subscription)

    }
    override fun getByTitle(title:String){
        publishSubject.onNext(title)

    }

    override fun getArhivedNotes() {
        val subscription = notesRepository
            .getArchived()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                notesState.value = NotesState.Archived(it)
            },{
                notesState.value = NotesState.Error("Error while fetching notes from db")
            })
        subsricptions.add(subscription)

    }

    override fun onCleared() {
        super.onCleared()
        subsricptions.dispose()
    }
}