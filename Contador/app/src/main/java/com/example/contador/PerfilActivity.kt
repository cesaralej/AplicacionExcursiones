package com.example.contador

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class ListaActivity : AppCompatActivity() {

    private var selectedJoven: Joven? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        Log.d(TAG, "Getting incoming Intent")
        selectedJoven = intent.getSerializableExtra(JOVEN_KEY) as Joven
        Log.d(TAG, "Got incoming Intent")

        val nombre = findViewById<TextView>(R.id.name_text)
        val foto = findViewById<ImageView>(R.id.imageView2)

        nombre.text = selectedJoven?.name + " " + selectedJoven?.lastName
        if (selectedJoven?.sex == "Hombre") foto.setImageResource(R.drawable.men) else foto.setImageResource(R.drawable.women)
        setInfo()


    }

    @SuppressLint("SetTextI18n")
    private fun setInfo() {
        Log.d(TAG, "Getting incoming Intent")
        val descripcion = findViewById<TextView>(R.id.textView2)
        descripcion.text = "ID: " + selectedJoven?.uniqueId + "\n" +
                "Nombre: " + selectedJoven?.name + "\n" +
                "Apellidos: " + selectedJoven?.lastName + "\n" +
                "Destino: " + selectedJoven?.destination + "\n\n" +

                "Telefono: " + selectedJoven?.phone + "\n" +
                "Correo: " + selectedJoven?.email + "\n\n" +

                "Colegio: " + selectedJoven?.school + "\n" +
                "Fecha de Nacimiento: " + selectedJoven?.day + "/" +
                selectedJoven?.month + "/" +
                selectedJoven?.year + "\n\n" +


                "Codio de Seguro: " + selectedJoven?.insuranceCode + "\n" +
                "Tipo de Sangre: " + selectedJoven?.bloodType + "\n\n" +

                "Pasaportes: " + selectedJoven?.passports + "\n" +
                "Pasaporte Colombiano: "  + "\n" +
                "Pasaporte Internacional: "  + "\n\n" +

                "\nNombre Padre: " + selectedJoven?.fatherName + selectedJoven?.lastName?.split(" ")?.get(0) + "\n" +
                "Telefono Padre: " + selectedJoven?.fatherPhone + "\n" +
                "\nNombre Madre: " + selectedJoven?.motherName + selectedJoven?.lastName?.split(" ")?.get(1) + "\n" +
                "Telefono Madre: " + selectedJoven?.motherPhone + "\n"
        }


    /*
    private fun setName(mName: String){
        Log.d(TAG, "Setting Name")
        var mNombre: TextView? = null
        mNombre
    }*/

    companion object {
        //5
        private val JOVEN_KEY = "JOVEN"
        private val TAG = "ListaActivity"

    }
}