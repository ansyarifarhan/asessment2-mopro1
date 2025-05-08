package com.ansyari0027.assesment2mobpro1.ui.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansyari0027.assesment2mobpro1.database.AnggotaDao
import com.ansyari0027.assesment2mobpro1.model.Anggota
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class MainViewModel(dao: AnggotaDao) : ViewModel() {

    val data: StateFlow<List<Anggota>> = dao.getCatatan().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}