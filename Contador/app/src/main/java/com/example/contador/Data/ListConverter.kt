package com.example.contador.Data

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class ListConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<Passport>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object: TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Passport>?): String? {
        return gson.toJson(someObjects)
    }
}