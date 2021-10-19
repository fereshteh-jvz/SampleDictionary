package com.shetabit.sampledictionary.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val definition: String = "",
    val example: String = ""
) {
    var isSelected = false
}