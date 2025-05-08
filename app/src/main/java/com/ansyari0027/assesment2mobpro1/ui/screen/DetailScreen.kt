package com.ansyari0027.assesment2mobpro1.ui.screen


import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ansyari0027.assesment2mobpro1.R
import com.ansyari0027.assesment2mobpro1.database.AnggotaDb
import com.ansyari0027.assesment2mobpro1.ui.theme.Assesment2Mobpro1Theme
import com.ansyari0027.assesment2mobpro1.util.ViewModelFactory

const val KEY_ID_ANGGOTA = "idAnggota"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = AnggotaDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var divisi by remember { mutableStateOf("IT") }
    var showDialog by remember { mutableStateOf(false) }

    val daftarDivisi = listOf("Gunung & Hutan", "ORAD", "Rock Climbing", "Jurnalistik")

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getAnggota(id) ?: return@LaunchedEffect
        nama = data.nama
        email = data.email
        divisi = data.divisi ?: "IT"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = if (id == null) "Tambah Anggota" else "Edit Anggota")
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (nama.isEmpty() || email.isEmpty() || divisi.isEmpty()) {
                            Toast.makeText(context, "Data tidak lengkap!", Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        if (id == null) {
                            viewModel.insert(nama, email, divisi)
                        } else {
                            viewModel.update(id, nama, email, divisi)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = "Simpan",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction {
                            showDialog = true
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Form Nama
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                label = { Text(text = "Nama Lengkap") },
                modifier = Modifier.fillMaxWidth()
            )

            // Form Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth()
            )

            // Pilihan Divisi
            Text(text = "Pilih Divisi:")
            daftarDivisi.forEach { itemDivisi ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (divisi == itemDivisi),
                        onClick = { divisi = itemDivisi }
                    )
                    Text(text = itemDivisi, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        if (id != null && showDialog) {
            DisplayAlertDialog(
                onDismissRequest = { showDialog = false }
            ) {
                showDialog = false
                viewModel.delete(id)
                navController.popBackStack()
            }
        }
    }
}


@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    Assesment2Mobpro1Theme {  DetailScreen(rememberNavController())  }
}
