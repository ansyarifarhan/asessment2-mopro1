package com.ansyari0027.assesment2mobpro1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ansyari0027.assesment2mobpro1.model.Anggota
import kotlinx.coroutines.flow.Flow

@Dao
interface AnggotaDao {
    @Insert
    suspend fun insert(anggota: Anggota)

    @Update
    suspend fun update(anggota: Anggota)

    @Query("SELECT * FROM anggota WHERE id = :id")
    suspend fun getAnggotaById(id: Long): Anggota?


    @Query("SELECT * FROM anggota")
    fun getCatatan(): Flow<List<Anggota>>

    @Query("DELETE FROM anggota WHERE id = :id")
    suspend fun deleteById(id: Long)
}