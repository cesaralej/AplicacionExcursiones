package com.example.tickettoto

import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import com.example.tickettoto.fragments.HomeFragment
import com.example.tickettoto.fragments.LoginFragment
import com.example.tickettoto.helpers.FragmentHandler
import com.example.tickettoto.helpers.Utils
import com.example.tickettoto.lib.Firestore
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NfcAdapter.ReaderCallback {

    private lateinit var fragmentHandler: FragmentHandler
    private lateinit var actionBar : ActionBar
    private lateinit var fragmentManager: FragmentManager
    private var nfcAdapter: NfcAdapter? = null
    var reading = false

    companion object {
        val TAG = "MAIN_ACTIVITY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        setSupportActionBar(main_toolbar)
        actionBar = supportActionBar!!
//        actionBar.setDisplayHomeAsUpEnabled(true)

        fragmentManager = supportFragmentManager
        fragmentHandler = FragmentHandler(this, R.id.main_fragment_container)

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            fragmentHandler.add(LoginFragment.getInstance())

        } else {
            fragmentHandler.add(HomeFragment.getInstance())
        }
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
    }

    override fun onTagDiscovered(tag: Tag?) {
        Log.d(TAG, "Card readed!")
        val parentLayout = findViewById<View>(android.R.id.content)
        Utils.showSnackbar(parentLayout ,"Card readed!, tag: $tag")
    }

    fun startReading() {
        if (!reading) {
            nfcAdapter?.enableReaderMode(this, this,
                    NfcAdapter.FLAG_READER_NFC_A or
                            NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                    null)
            reading = true
        }
    }

    fun stopReading() {
        if (reading) {
            nfcAdapter?.disableReaderMode(this)
            reading = false
        }
    }


//    override fun onBackPressed() {
//        super.onBackPressed()
//        this.finish()
//    }
    //    private val navigationBackPressListener = View.OnClickListener { fragmentManager.popBackStack() }
}
