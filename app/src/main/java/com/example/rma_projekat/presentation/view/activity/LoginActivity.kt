package com.example.rma_projekat.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rma_projekat.R
import com.example.rma_projekat.presentation.Konstants
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:AppCompatActivity(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }
    private fun initListener(){
        prijavaBtn.setOnClickListener {
            val editor = getSharedPreferences(packageName,Context.MODE_PRIVATE).edit()
            val name = nameET.text.toString()
            val pin = Integer.parseInt(pinET.text.toString())

            if(name.isNullOrBlank() || pin !=1234){
                Toast.makeText(this,"Kredencijali lose uneti",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            editor.putBoolean(Konstants.LOGGED,true)
            editor.putString(Konstants.LOGGED_NAME,name)
            editor.apply()
            val intent = Intent(this,SchedulerActivity::class.java)
            startActivity(intent)
            finish()

        }
    }



}