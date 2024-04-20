package com.edavilaz.movil

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edavilaz.movil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //para que funcione el viewBinding se apunta al archivo del layout _ = mayúscula
        // activity_main = activityMainBinding (siempre Binding al final)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.viewUser.setOnClickListener { navigateToUserActivity() }
        binding.viewLogin.setOnClickListener { navigateToLoginActivity() }
        binding.viewCart.setOnClickListener { navigateToCartActivity() }
        binding.viewProducts.setOnClickListener { navigateToProductActivity() }
//        binding.viewLogout.setOnClickListener { navigateToLogoutActivity() }

    }
    private fun navigateToUserActivity(){
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToCartActivity(){
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToProductActivity(){
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }
//    private fun navigateToLogoutActivity() {
//        val intent = Intent(this, LogoutActivity::class.java)
//        startActivity(intent)
//    }

}