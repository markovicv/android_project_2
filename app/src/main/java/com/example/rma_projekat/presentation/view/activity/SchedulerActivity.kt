package com.example.rma_projekat.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma_projekat.R
import com.example.rma_projekat.data.model.Filter
import com.example.rma_projekat.presentation.adapter.SchedulesAdapter
import com.example.rma_projekat.presentation.contracts.SchedulerContract
import com.example.rma_projekat.presentation.view.state.SchedulerState
import com.example.rma_projekat.presentation.viewmodel.SchedulerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SchedulerActivity : AppCompatActivity() {
    private val mainViewModel:SchedulerContract.ViewModel by viewModel<SchedulerViewModel>()
    private var itemList = arrayOf("dan","ponedeljak","utorak","sreda")
    private var arrayAdapter: ArrayAdapter<String>?=null
    private lateinit var schedulerAdapter:SchedulesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFiltririaj.setOnClickListener {
            val grupa =grupaSearchId.text.toString()
            val dan = daySpiner.selectedItem.toString()
            val predmetProfesor = searchText.text.toString()

            val filter = Filter(grupa,dan,predmetProfesor)
            mainViewModel.getFiltered(filter)
        }
        schedulerRv.layoutManager = LinearLayoutManager(this)
        schedulerAdapter = SchedulesAdapter()
        schedulerRv.adapter = schedulerAdapter

        mainViewModel.schedulerState.observe(this, Observer {
            renderState(it)
        })
        mainViewModel.getAllSchedulers()
        mainViewModel.fetchSchedulers()

        arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1,itemList)
        daySpiner.adapter = arrayAdapter
    }
    private fun renderState(state:SchedulerState){
        when(state){
            is SchedulerState.Succes->{
                schedulerAdapter.setSchedulerList(state.schedulers)
             }
            is SchedulerState.Error->{
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is SchedulerState.DataFetched->{
                Toast.makeText(this, "Fresh data from server", Toast.LENGTH_SHORT).show()
            }
            is SchedulerState.Loading->{

            }
        }
    }
}
