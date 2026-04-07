package com.zalfashabrina0019.assesment01.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zalfashabrina0019.assesment01.ui.screen.FormScreen
import com.zalfashabrina0019.assesment01.ui.screen.KatalogScreen
import com.zalfashabrina0019.assesment01.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable (route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable (route = Screen.Katalog.route) {
            KatalogScreen(navController)
        }
        composable (route = Screen.Form.route) {
            FormScreen(navController)
        }
    }
}