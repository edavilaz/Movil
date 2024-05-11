package com.edavilaz.movil

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edavilaz.movil.databinding.ActivityProductBinding
import com.google.firebase.firestore.FirebaseFirestore

class ProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db:FirebaseFirestore = FirebaseFirestore.getInstance()

        val binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Consultar Productos
        binding.Consultar.setOnClickListener {

            var datos =""
            db.collection("products")
                .get()
                .addOnSuccessListener { resultado ->
                    for(documento in resultado){
                        datos += "${documento.id}: ${documento.data}\n"
                    }
                    binding.txtResultado.text = datos

                }
                .addOnFailureListener{ exception ->
                    binding.txtResultado.text = "No se ha podido conectar a la BD"
                }
        }

        // Guardar Producto

        binding.Guardar.setOnClickListener {
            guardarDatos(binding, db)

        }

        // Modificar Producto

        binding.Modificar.setOnClickListener {
            guardarDatos(binding, db)
        }

        // Borrar Producto

        binding.Borrar.setOnClickListener {
            if(binding.id.text.isNotBlank()){
                db.collection("products")
                    .document(binding.id.text.toString())
                    .delete()
                    .addOnSuccessListener { _ ->
                        binding.txtResultado.text = "Producto Eliminado"
                    }
                    .addOnFailureListener { _ ->
                        binding.txtResultado.text = "Error al eliminar el producto"
                    }
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // se usa la misma función para guardar y para modificar datos
    private fun guardarDatos(
        binding: ActivityProductBinding,
        db: FirebaseFirestore
    ) {
        if (binding.id.text.isNotBlank() &&
            binding.nombre.text.isNotBlank() &&
            binding.descripcion.text.isNotBlank() &&
            binding.precio.text.isNotBlank()
        ) {

            val dato = hashMapOf(
                //"id" to binding.id.text
                "nombre" to binding.nombre.text.toString(),
                "descripcion" to binding.descripcion.text.toString(),
                "precio" to binding.precio.text.toString()
            )

            db.collection("products")
                .document(binding.id.text.toString())
                .set(dato)
                .addOnSuccessListener { _ ->
                    binding.txtResultado.text = "Procesado Correctamente"
                }
                .addOnFailureListener { _ ->
                    binding.txtResultado.text = "No se ha podido Procesar"
                }
        }
    }
}