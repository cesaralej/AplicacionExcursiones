package com.example.contador.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Passport (
    @PrimaryKey(autoGenerate = true)
    val passportId: Int? = null,
    val countryName: String,
    val passportNumber: Int
)