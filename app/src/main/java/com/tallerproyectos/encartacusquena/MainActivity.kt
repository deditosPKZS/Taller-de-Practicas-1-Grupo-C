package com.tallerproyectos.encartacusquena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.tallerproyectos.encartacusquena.ui.theme.EncartaCusquenaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = Repository(this)

        setContent {
            EncartaCusquenaTheme {
                val navController = rememberNavController()
                AppNavGraph(navController, repository)
            }
        }
    }
}
