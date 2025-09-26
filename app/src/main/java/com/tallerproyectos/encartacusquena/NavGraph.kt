package com.tallerproyectos.encartacusquena

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController, repository: Repository) {
    NavHost(navController = navController, startDestination = "categorias") {
        composable("categorias") {
            CategoriaScreen(
                categorias = repository.getCategorias(),
                onCategoriaClick = { categoria ->
                    navController.navigate("preguntas/${Uri.encode(categoria)}")
                }
            )
        }

        composable("preguntas/{categoria}") { backStackEntry ->
            val categoria = backStackEntry.arguments?.getString("categoria")?.let { Uri.decode(it) } ?: ""
            // Ojo: adapta este repo call para obtener preguntas por nombre o por id
            val preguntas = repository.getPreguntasPorCategoria(1) // <-- placeholder: reemplaza por la lógica real
            PreguntaScreen(
                preguntas = preguntas,
                categoria = categoria,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
