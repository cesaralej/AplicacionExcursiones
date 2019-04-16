package com.example.contador

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

@SuppressLint("Registered")
class CompleteListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_list)

        val listaJovenes = Joven.getJovenesFromFile("jovenes.json", this)

        val recyclerView = findViewById<RecyclerView>(R.id.lista_jovenes)


        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = RecyclerAdapter(listaJovenes)
    }

}