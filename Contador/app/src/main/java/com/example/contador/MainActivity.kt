package com.example.contador

import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.contador.Data.AppDatabase
import com.example.contador.Data.Student
import com.example.contador.Data.StudentDAO
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var studentDAO: StudentDAO? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnOpenList: Button = findViewById(R.id.list_button)
        val btnNfc: Button = findViewById(R.id.nfc_button)

        btnOpenList.setOnClickListener {
            val intent = Intent(this, CompleteListActivity::class.java)
            startActivity(intent)
        }

        Observable.fromCallable({
            db = AppDatabase.getAppDatabase(context = this)
            studentDAO = db?.studentDao()

            var student1 = Student(name = "Mauricio")
            var student2 = Student(name = "Cesar")

            with(studentDAO) {
                this?.insertStudent(student1)
                this?.insertStudent(student2)
            }
            db?.studentDao()?.getStudents()
        }).doOnNext({ list ->
            var finalString = ""
            list?.map { finalString += it.name + " - " }
            Log.d("MAU", finalString)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

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
