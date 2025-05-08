package com.ansyari0027.assesment2mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansyari0027.assesment2mobpro1.database.AnggotaDao
import com.ansyari0027.assesment2mobpro1.model.Anggota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailViewModel(private val dao: AnggotaDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(nama: String, email: String, divisi: String) {
        val anggota = Anggota(
            tanggal = formatter.format(Date()),
            nama = nama,
            email = email,
            divisi = divisi
        )
        viewModelScope.launch(Dispatchers.IO) { dao.insert(anggota) }
    }

    suspend fun getAnggota(id: Long): Anggota? = dao.getAnggotaById(id)

    fun update(id: Long, nama: String, email: String, divisi: String) {
        val anggota = Anggota(id, formatter.format(Date()), nama, email, divisi)
        viewModelScope.launch(Dispatchers.IO) { dao.update(anggota) }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) { dao.deleteById(id) }
    }
}
