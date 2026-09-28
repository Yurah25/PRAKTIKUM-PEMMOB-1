package com.yurahco.yusufcoba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yurahco.yusufcoba.ui.screen.DaftarProdukScreen
import com.yurahco.yusufcoba.ui.screen.DetailProductScreen
import com.yurahco.yusufcoba.ui.screen.HubungiKamiScreen
import com.yurahco.yusufcoba.ui.theme.YusufCobaTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            YusufCobaTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "daftar_produk"
                ) {
                    composable(
                        route = "daftar_produk"
                    ) {
                        DaftarProdukScreen(
                            navController = navController
                        )
                    }

                    composable(
                        route = "detail/{productId}",
                        arguments = listOf(
                            navArgument("productId") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->

                        val productId =
                            backStackEntry.arguments
                                ?.getInt("productId")
                                ?: 0

                        DetailProductScreen(
                            productId = productId,
                            navController = navController
                        )
                    }

                    composable(
                        route = "hubungi_kami"
                    ) {
                        HubungiKamiScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}