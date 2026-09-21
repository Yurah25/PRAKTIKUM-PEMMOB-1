package com.yurahco.yusufcoba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.yurahco.yusufcoba.ui.screen.DaftarProdukScreen
import com.yurahco.yusufcoba.ui.theme.YusufCobaTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            YusufCobaTheme {
                DaftarProdukScreen()
            }
        }
    }
}