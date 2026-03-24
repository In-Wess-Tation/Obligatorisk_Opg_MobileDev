package com.example.obligatorisk_opg_2.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Birthday(
    @SerialName("id")
    val id: Int,

    @SerialName("userId")
    val userId: String,

    @SerialName("name")
    val name: String,

    @SerialName("birthYear")
    val birthYear: Int,

    @SerialName("birthMonth")
    val birthMonth: Int,

    @SerialName("birthDayOfMonth")
    val birthDayOfMonth: Int,

    @SerialName("remark")
    val remark: String,

    @SerialName("age")
    val age: Int


)