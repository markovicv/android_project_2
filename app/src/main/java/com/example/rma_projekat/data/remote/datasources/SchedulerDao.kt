package com.example.rma_projekat.data.remote.datasources

import androidx.room.*
import com.example.rma_projekat.data.model.SchedulerEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class SchedulerDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: SchedulerEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SchedulerEntity>): Completable

    @Query("DELETE FROM schedulers")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<SchedulerEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
    @Query("SELECT * FROM schedulers")
    abstract fun getAll(): Observable<List<SchedulerEntity>>
}