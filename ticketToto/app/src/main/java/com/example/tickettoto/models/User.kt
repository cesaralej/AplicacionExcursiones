package com.example.tickettoto.models


import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

//@Parcelize
//data class User(
//    val name: String?,
//    val email: String?,
//    val destination: String?,
//    val phone: String?,
//    val passports: String?,
//    var school: String?,
//    val day: String?,
//    val month: String?,
//    val year: String?,
//    var status: Boolean?
//) : Parcelable

@IgnoreExtraProperties
data class User(
    var name: String? = "",
    var email: String? = "",
    var status: Boolean? = false
)
