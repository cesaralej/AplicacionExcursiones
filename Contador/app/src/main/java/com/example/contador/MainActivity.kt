package com.example.contador

import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnOpenList: Button = findViewById(R.id.list_button)
        val btnNfc: Button = findViewById(R.id.nfc_button)

        btnOpenList.setOnClickListener {
            val intent = Intent(this, CompleteListActivity::class.java)
            startActivity(intent)
        }

        btnNfc.setOnClickListener {
            val intent = Intent(this, NfcActivity::class.java)
            startActivity(intent)
        }


        var nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        Log.d("NFC supported", (nfcAdapter != null).toString())
        Log.d("NFC enabled", (nfcAdapter?.isEnabled).toString())
    }



    // populate the views now that the layout has been inflated




}
