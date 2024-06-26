package com.edavilaz.movil

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper(context: Context): SQLiteOpenHelper(
    context, "users.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE users " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, email TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS users"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun agregarUsuario(nombre: String, email: String){
        var datos = ContentValues()
        datos.put(/* key = */ "nombre", /* value = */ nombre)
        datos.put(/* key = */ "email", /* value = */ email)

        val db = this.writableDatabase
        db.insert("users",null,datos)
        db.close()

    }
    fun borrarUsuario(id: Int):Int {
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val borrados = db.delete("users","id = ?", args)
        // db.execsSQL("DELETE FROM users WHERE _id = ?",args)
        db.close()
        return borrados

    }

   fun modificarUsuario(id: Int, nombre: String, email: String){
        val args = arrayOf(id.toString())

        var datos = ContentValues()
        datos.put(/* key = */ "nombre", /* value = */ nombre)
        datos.put(/* key = */ "email", /* value = */ email)

        val db = this.writableDatabase
        db.update("users", datos,"id = ?", args)
        db.close()

    }
}