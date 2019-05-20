package com.example.contador.Data

import android.arch.persistence.room.*

@Dao
interface StudentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * FROM Student WHERE name == :name")
    fun getStudentByName(name: String): List<Student>

    @Query("SELECT * FROM Student")
    fun getStudents(): List<Student>
}