package org.d3if3034.miniproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "praktikum")
data class Praktikum(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val lab: String,
    val rangkuman: String,
    val tanggal: String
)
