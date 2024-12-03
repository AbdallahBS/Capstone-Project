package com.example.abdallah.ui

sealed class Screen(val route:String) {
    data object EnterpriseListScreen : Screen("enterprise_list_screen")
    data object EnterpriseDetailScreen: Screen("enterprise_detail_screen")
}