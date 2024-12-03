package com.example.abdallah.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.rememberNavController
import com.example.abdallah.common.Constants

import com.example.abdallah.ui.theme.CryptoTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.composable
import com.example.abdallah.ui.enterprise_detail.ui_elements.EnterpriseDetailScreen
import com.example.abdallah.ui.enterprise_list.ui_elements.EnterpriseListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.EnterpriseListScreen.route
                    ) {
                        composable(
                            route = Screen.EnterpriseListScreen.route
                        ) {
                            EnterpriseListScreen(navController)
                        }
                        composable(
                            route = Screen.EnterpriseDetailScreen.route + "/{${Constants.PARAM_ENTERPRISE_ID}}"
                        ) {
                            EnterpriseDetailScreen()
                        }
                    }
                }
            }
        }
    }
}