package com.example.tickettoto.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val id: String?,
        val nfcCode: Number?,
        val name: String?,
        val email: String?,
        val destination: String?,
        val phone: String?,
        val passports: String?,
        var school: String?,
        val day: String?,
        val month: String?,
        val year: String?,
        var status: Boolean?,
        val profilePicture: String?
) : Parcelable