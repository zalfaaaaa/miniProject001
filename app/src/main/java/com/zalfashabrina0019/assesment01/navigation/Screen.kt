package com.zalfashabrina0019.assesment01.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object Katalog: Screen("katalogscreen")
    data object Form: Screen("formscreen")
}