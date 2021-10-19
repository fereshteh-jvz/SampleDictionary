package com.shetabit.sampledictionary.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "UserWords",
)
data class UserWordsEntity(
    @PrimaryKey(autoGenerate = false)
    val wordRef: Int
) {
    var isSelected = wordRef > 0
}