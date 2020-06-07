package com.example.rma_projekat.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rma_projekat.MainActivity
import com.example.rma_projekat.presentation.view.activity.LoginActivity
import com.example.rma_projekat.presentation.view.activity.NoteActivity

class SplashScreenActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreference = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val logged = sharedPreference.getBoolean("logged",false)

        if(logged){
            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this,
                LoginActivity::class.java)
            startActivity(intent)
        }
        finish()




    }
}