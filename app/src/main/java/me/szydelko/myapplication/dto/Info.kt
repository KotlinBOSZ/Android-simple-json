package me.szydelko.myapplication.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("dataczas")
    val dataczas: String,
    @SerialName("miasto")
    val miasto: String,
    @SerialName("prowadzacy")
    val prowadzacy: String,
    @SerialName("przedmiot")
    val przedmiot: String,
    @SerialName("szkola")
    val szkola: String
)