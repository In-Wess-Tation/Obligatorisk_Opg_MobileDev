package com.example.obligatorisk_opg_2.data

import com.google.gson.annotations.SerializedName

data class Birthday(
    @SerializedName("id")
    val id: Int,

    @SerializedName("userId")
    val userId: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("birthYear")
    val birthYear: Int,

    @SerializedName("birthMonth")
    val birthMonth: Int,

    @SerializedName("birthDayOfMonth")
    val birthDayOfMonth: Int,

    @SerializedName("remarks")
    val remarks: String?,

    @SerializedName("age")
    val age: Int
)