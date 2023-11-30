package me.szydelko.myapplication.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Le(
    @SerialName("Grupa1")
    val grupa1: Map<String, String>,
    @SerialName("Grupa2")
    val grupa2: Map<String, String>,
    @SerialName("info")
    val info: Info

)