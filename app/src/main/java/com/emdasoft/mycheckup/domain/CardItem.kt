package com.emdasoft.mycheckup.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardItem(
    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    var amount: Double,

    @ColumnInfo
    val currency: String,

    @ColumnInfo
    val category: String,
)