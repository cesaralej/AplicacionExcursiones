package com.example.contador

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnOpenList: Button = findViewById(R.id.list_button)

        btnOpenList.setOnClickListener {
            val intent = Intent(this, CompleteListActivity::class.java)
            startActivity(intent)

    }


    }



    // populate the views now that the layout has been inflated




}
