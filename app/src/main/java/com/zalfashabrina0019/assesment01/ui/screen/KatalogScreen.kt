package com.zalfashabrina0019.assesment01.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zalfashabrina0019.assesment01.R
import com.zalfashabrina0019.assesment01.model.Ruangan
import com.zalfashabrina0019.assesment01.ui.theme.Assesment01Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatalogScreen(navController: NavHostController) {
    val data = listOf(
        Ruangan(stringResource(R.string.ruangRapat), stringResource(R.string.desc_rapat), R.drawable.meetingroom),
        Ruangan( stringResource(R.string.ruangPermainan), stringResource(R.string.desc_permainan), R.drawable.psroom),
        Ruangan( stringResource(R.string.ruangMusik), stringResource(R.string.desc_musik), R.drawable.musicstudio),
        Ruangan( stringResource(R.string.ruangNonton), stringResource(R.string.desc_nonton), R.drawable.movieroom)

    )

    var index by remember { mutableIntStateOf(0) }

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
                    Text(text = stringResource(id = R.string.katalogKami))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        ScreenKatalogContent(data[index], Modifier.padding(innerPadding)) {
            index = if (index == data.size-1) 0 else index + 1
        }
    }
}

@Composable
fun ScreenKatalogContent(ruangan: Ruangan, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val localFont = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_medium, FontWeight.Medium)
    )

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = ruangan.imageResId),
            contentDescription = stringResource(R.string.gambar, ruangan.nama, ruangan.descRuang),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(190.dp)
        )
        Text(
            text = ruangan.nama,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = localFont,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = ruangan.descRuang,
            fontFamily = localFont,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 3.dp)
        )
        Button(
            onClick = { onClick() },
            modifier = Modifier.fillMaxWidth(0.5f).padding(top = 24.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = stringResource(R.string.lanjut))
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KatalogScreenPreview() {
    Assesment01Theme {
        KatalogScreen(rememberNavController())
    }
}