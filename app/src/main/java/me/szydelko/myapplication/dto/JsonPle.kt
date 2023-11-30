package me.szydelko.myapplication.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonPle(
    @SerialName("completed")
    val completed: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int
)