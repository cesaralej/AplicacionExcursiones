package com.example.contador.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

@Entity
data class Student (
    @PrimaryKey(autoGenerate = true)
    val studentId: Int? = null,
    val name: String
    /*val lastName: String,
    val gender: String,
    val phone: String,
    val email: String,
    val day: Int,
    val month: Int,
    val year: Int,
    val schoolName: String,
    val secureNumber: Int,
    val bloodType: String,
    @TypeConverters(ListConverter::class)
    val passports: List<Passport>,
    val destination: String,
    val fatherName: String,
    val fatherPhone: Int,
    val motherName: String,
    val motherPhone: Int*/
    )

/*"id": 1,
"Nombre": "Benjamin",
"Apellido": "Gonz\u00e1lez Hoskinson",
"Sexo": "Hombre",
"Celular": "188-572-3435",
"Email": "bgonzlez315@hotmail.com",
"Dia": "12",
"Mes": "8",
"Year": "2005",
"Colegio": "Forman",
"Numero Seguro": 1506836,
"Tipo de Sangre": "AB-positive",
"Pasaportes": {
    "Colombia": "5266860",
    "Italia": "3861057"
},
"Destino": "Madrid",
"Nombre Padre": "John",
"Celular Padre": "729-545-2474",
"Madre": "Patricia",
"Celular Madre": "471-524-4458" */