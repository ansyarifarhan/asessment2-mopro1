package com.ansyari0027.assesment2mobpro1.navigation

import com.ansyari0027.assesment2mobpro1.ui.screen.KEY_ID_ANGGOTA

sealed class Screen(val route: String) {
    data object Home: Screen("MainScreen")
    data object FormBaru: Screen("DetailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_ANGGOTA}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}