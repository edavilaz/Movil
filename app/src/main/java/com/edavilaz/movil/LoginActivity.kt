package com.edavilaz.movil

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edavilaz.movil.databinding.ActivityLoginBinding
import com.edavilaz.movil.databinding.ActivityRegistroBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnRegistro.setOnClickListener { navigateToRegistroActivity() }
        binding.btnLogin.setOnClickListener { navigateToProductActivity() }

    }

    private fun navigateToRegistroActivity(){
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)

    }

    private fun navigateToProductActivity(){
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }
}