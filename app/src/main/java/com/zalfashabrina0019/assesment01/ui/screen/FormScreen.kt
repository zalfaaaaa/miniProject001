package com.zalfashabrina0019.assesment01.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zalfashabrina0019.assesment01.R
import com.zalfashabrina0019.assesment01.ui.theme.Assesment01Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.mulaiRent))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        ScreenFormContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenFormContent(modifier: Modifier = Modifier) {
    val localFont = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_medium, FontWeight.Medium)
    )

    var nama by rememberSaveable { mutableStateOf("") }
    var namaError by rememberSaveable { mutableStateOf(false) }

    val image = painterResource(R.drawable.marurent)

    var durasi by rememberSaveable { mutableStateOf("") }
    var durasiError by rememberSaveable { mutableStateOf(false) }

    val daftarRuangan = listOf(
        stringResource(id = R.string.ruangRapat),
        stringResource(id = R.string.ruangPermainan),
        stringResource(id = R.string.ruangMusik),
        stringResource(id = R.string.ruangNonton)
    )

    var ruanganTerpilih by rememberSaveable { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }

    val daftarHarga = listOf( 10000, 15000, 20000, 25000 )

    var hargaTerpilih by rememberSaveable { mutableStateOf(0) }
    var hargaExpanded by remember { mutableStateOf(false) }
    var hargaError by rememberSaveable { mutableStateOf(false) }

    var totalHarga by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(26.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = image,
                contentDescription = stringResource(R.string.gambar),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(65.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = localFont,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 15.dp),
            )
        }
        OutlinedTextField(
            value = nama,
            onValueChange = {
                nama = it
                namaError = false
            },
            label = { Text(text = stringResource(id = R.string.nama)) },
            supportingText = { ErrorHint(namaError) },
            isError = namaError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = ruanganTerpilih,
                onValueChange = {},
                readOnly = true,
                label = { Text("Ruangan") },
                placeholder = { Text("Pilih ruangan") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                daftarRuangan.forEach { ruangan ->
                    DropdownMenuItem(
                        text = { Text(ruangan) },
                        onClick = {
                            ruanganTerpilih = ruangan
                            isExpanded = false
                        }
                    )
                }
            }
        }
        OutlinedTextField(
            value = durasi,
            onValueChange = { durasi = it },
            label = { Text(text = stringResource(R.string.durasi)) },
            trailingIcon = { IconPicker(durasiError, "jam") },
            supportingText = { ErrorHint(durasiError) },
            isError = durasiError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenuBox(
            expanded = hargaExpanded,
            onExpandedChange = { hargaExpanded = !hargaExpanded }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = if (hargaTerpilih == 0) "" else hargaTerpilih.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.harga)) },
                placeholder = { Text(stringResource(R.string.pilihHarga)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = hargaExpanded) },
            )
            ExposedDropdownMenu(
                expanded = hargaExpanded,
                onDismissRequest = { hargaExpanded = false }
            ) {
                daftarHarga.forEach { harga ->
                    DropdownMenuItem(
                        text = { Text("Rp $harga") },
                        onClick = {
                            hargaTerpilih = harga
                            hargaExpanded = false
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                durasiError = (durasi == "" || durasi == "0")
                hargaError = hargaTerpilih == 0
                if (durasiError || hargaError) return@Button

                totalHarga = hitungTotal(durasi.toInt(), hargaTerpilih)
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.simpan))
        }
        if (totalHarga != 0) {
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Total Harga: Rp $totalHarga",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = null
        )
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(
            text = stringResource(R.string.input_invalid)
        )
    }
}

private fun hitungTotal(durasi: Int, harga: Int): Int {
    return durasi * harga
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun FormScreenPreview() {
    Assesment01Theme {
        FormScreen(rememberNavController())
    }
}