package com.example.mvvmsampleapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val quote: String,
    val author:String,
    val thumbnail:String,
    @ColumnInfo(defaultValue = "")
    val created_at:String,
    @ColumnInfo(defaultValue = "")
    val updated_at:String
)