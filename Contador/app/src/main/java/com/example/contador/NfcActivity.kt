package com.example.contador

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.example.contador.Data.AppDatabase
import kotlin.experimental.and

class NfcActivity : AppCompatActivity(), NfcAdapter.ReaderCallback {

    private var nfcAdapter: NfcAdapter? = null
    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)

        val btnOpenList: Button = findViewById(R.id.nfcbutton1)

        btnOpenList.setOnClickListener {
            Log.i(TAG, "New tag discovered")
        }
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

    }

    public override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(this)
    }

    public override fun onResume() {
        super.onResume()
        nfcAdapter?.enableReaderMode(this, this,
            NfcAdapter.FLAG_READER_NFC_A or
                    NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null)
    }

    override fun onTagDiscovered(tag: Tag?) {
        Log.d(TAG, "Tarjeta Leida")
        var tagIdBytes = tag!!.id
        var tagIdString = ByteArrayToHexString(tagIdBytes)
        Log.d(TAG, tagIdString)
    }

    private fun ByteArrayToHexString(inarray: ByteArray): String {
        var i: Int
        var j: Int
        var `in`: Int
        val hex = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var out = ""

        j = 0
        while (j < inarray.size) {
            `in` = inarray[j].toInt() and 0xff
            i = `in` shr 4 and 0x0f
            out += hex[i]
            i = `in` and 0x0f
            out += hex[i]
            ++j
        }
        return out
    }




    companion object {

        val TAG = "CardReaderFragment"
        // Recommend NfcAdapter flags for reading from other Android devices. Indicates that this
        // activity is interested in NFC-A devices (including other Android devices), and that the
        // system should not check for the presence of NDEF-formatted data (e.g. Android Beam).
        var READER_FLAGS = NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK
    }
}