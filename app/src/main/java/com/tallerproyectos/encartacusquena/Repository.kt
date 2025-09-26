package com.tallerproyectos.encartacusquena

import android.content.Context
import android.database.Cursor

class Repository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun getCategorias(): List<String> {
        val categorias = mutableListOf<String>()
        val db = dbHelper.openDatabase()
        val cursor: Cursor = db.rawQuery("SELECT nombre FROM categoria", null)
        while (cursor.moveToNext()) {
            categorias.add(cursor.getString(0))
        }
        cursor.close()
        db.close()
        return categorias
    }

    fun getPreguntasPorCategoria(categoriaId: Int): List<String> {
        val preguntas = mutableListOf<String>()
        val db = dbHelper.openDatabase()
        val cursor: Cursor = db.rawQuery(
            "SELECT enunciado FROM pregunta WHERE categoria_id = ?",
            arrayOf(categoriaId.toString())
        )
        while (cursor.moveToNext()) {
            preguntas.add(cursor.getString(0))
        }
        cursor.close()
        db.close()
        return preguntas
    }
}
