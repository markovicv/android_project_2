package com.example.rma_projekat.app

import android.app.Application
import android.app.TimePickerDialog

import com.example.rma_projekat.modules.codeModule
import com.example.rma_projekat.modules.notesModule
import com.example.rma_projekat.modules.schedulerModul
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class ApplicationProject:Application(){
    override fun onCreate() {
        super.onCreate()
        init();
    }

    private fun init(){
        initKoin()
        initTimber()
    }

    private fun initTimber(){
        Timber.plant(Timber.DebugTree())
    }
    private fun initKoin(){
        val modules = listOf(
            codeModule,
            schedulerModul,
            notesModule
        )
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ApplicationProject)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }

}