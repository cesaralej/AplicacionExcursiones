package com.example.tickettoto.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val id: String,
        var tag: String?,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phone: String,
        val birthday: String,
        var company: String,
        val passport: String,
        val destination: String,
        val familiarFullName: String,
        val familiarPhone: String,
        val profilePicture: ImageObject,
        var status: Boolean
) : Parcelable