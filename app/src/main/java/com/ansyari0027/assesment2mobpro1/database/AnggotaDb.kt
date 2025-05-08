package com.ansyari0027.assesment2mobpro1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ansyari0027.assesment2mobpro1.model.Anggota


@Database(entities = [Anggota::class], version = 1, exportSchema = false)
abstract class AnggotaDb : RoomDatabase() {
    abstract val dao: AnggotaDao

    companion object {
        @Volatile private var INSTANCE: AnggotaDb? = null

        fun getInstance(context: Context): AnggotaDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnggotaDb::class.java,
                    "anggota.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}