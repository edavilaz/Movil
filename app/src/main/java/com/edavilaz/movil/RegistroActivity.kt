package com.edavilaz.movil

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edavilaz.movil.databinding.ActivityRegistroBinding


class RegistroActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistroBinding
    lateinit var usersDBHelper:miSQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersDBHelper = miSQLiteHelper(this)

        binding.btnCrearUsuario.setOnClickListener {
            if(binding.txtUsuario.text.isNotBlank() && binding.txtEmail.text.isNotBlank()){
                usersDBHelper.agregarUsuario(binding.txtUsuario.text.toString(),
                    binding.txtEmail.text.toString() )
                binding.txtUsuario.text.clear()
                binding.txtEmail.text.clear()

               Toast.makeText(this,"GUARDADO",Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this,"No se ha podido guardar", Toast.LENGTH_LONG).show()
            }

        }

        binding.btnConsultar.setOnClickListener {
            binding.txtConsulta.text = ""
            val db: SQLiteDatabase = usersDBHelper.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM users",
                null)

            if (cursor.moveToFirst()) {
                do {
                    binding.txtConsulta.append(cursor.getInt(0).toString()+ ": ")
                    binding.txtConsulta.append(cursor.getString(1).toString()+ ", ")
                    binding.txtConsulta.append(cursor.getString(2).toString()+ "\n")
                } while (cursor.moveToNext())
            }

        }

        binding.btnEliminar.setOnClickListener {
            var cantidad = 0

            if(binding.txtId.text.isNotBlank() && binding.txtEmail.text.isNotBlank()){
                cantidad = usersDBHelper.borrarUsuario(binding.txtId.text.toString().toInt() )
                binding.txtId.text.clear()

            }
            else
            {
                Toast.makeText(this,"Datos Borrados: $cantidad", Toast.LENGTH_LONG).show()
            }
        }

            binding.btnModificar.setOnClickListener {
                if(binding.txtUsuario.text.isNotBlank() && binding.txtEmail.text.isNotBlank()
                    && binding.txtId.text.isNotBlank()){
                    usersDBHelper.modificarUsuario(
                        binding.txtId.text.toString().toInt(),
                        binding.txtUsuario.text.toString(),
                        binding.txtEmail.text.toString() )
                    binding.txtId.text.clear()
                    binding.txtUsuario.text.clear()
                    binding.txtEmail.text.clear()

                    Toast.makeText(this,"Se ha Modificado",Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this,"No se ha podido Modificar", Toast.LENGTH_LONG).show()
                }
            }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}


