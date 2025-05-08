package com.ansyari0027.assesment2mobpro1.util


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ansyari0027.assesment2mobpro1.database.AnggotaDao

import com.ansyari0027.assesment2mobpro1.ui.screen.DetailViewModel
import com.ansyari0027.assesment2mobpro1.ui.screen.MainViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val dao: AnggotaDao
) : ViewModelProvider.Factory {
    @Suppress("unhecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}