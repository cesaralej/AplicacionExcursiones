package com.example.contador.Data

import android.arch.persistence.room.*

@Dao
interface PassportDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(passport: Passport)

    @Update
    fun updateStudent(passport: Passport)

    @Delete
    fun deleteStudent(passport: Passport)

    @Query("SELECT * FROM Passport WHERE passportNumber == :passportNumber")
    fun getPassportsByNumber(passportNumber: Int): List<Passport>

    @Query("SELECT * FROM Passport")
    fun getPassports(): List<Passport>
}