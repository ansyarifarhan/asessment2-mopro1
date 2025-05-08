package com.ansyari0027.assesment2mobpro1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anggota")
data class Anggota(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tanggal: String,
    val nama: String,
    val email: String,
    val divisi: String
)



