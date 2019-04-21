package com.example.contador.Data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Student::class, Passport::class], version = 1)

abstract class AppDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDAO
    abstract fun passportDao(): PassportDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "excursionDb"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}