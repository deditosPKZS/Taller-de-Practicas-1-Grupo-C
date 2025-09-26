package com.tallerproyectos.encartacusquena

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "Encarta.db"   // <-- Asegúrate que coincida con el archivo en assets
        private const val DB_VERSION = 1
        private const val TAG = "DatabaseHelper"
    }

    private val dbPath: String
        get() = context.getDatabasePath(DB_NAME).path

    override fun onCreate(db: SQLiteDatabase?) {
        // No usamos onCreate porque la DB viene preconstruida en assets
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Aquí manejas migraciones si actualizas la DB en el futuro
    }

    @Synchronized
    fun openDatabase(): SQLiteDatabase {
        try {
            copyDatabaseIfNeeded()
        } catch (e: IOException) {
            Log.e(TAG, "Error copiando DB: ${e.message}")
        }
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    @Throws(IOException::class)
    private fun copyDatabaseIfNeeded() {
        val dbFile = File(dbPath)
        if (!dbFile.exists()) {
            dbFile.parentFile?.mkdirs()
            context.assets.open(DB_NAME).use { input ->
                FileOutputStream(dbFile).use { output ->
                    input.copyTo(output)
                }
            }
            Log.d(TAG, "DB copiada a $dbPath")
        } else {
            Log.d(TAG, "DB ya existe en $dbPath")
        }
    }
}
