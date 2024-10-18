package com.example.appdepizzaria

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appdepizzaria.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout XML usando o ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o clique no botão "Entrar"
        binding.btnLogin.setOnClickListener {
            // Navega para a MainActivity após o login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Para não permitir que o usuário volte para a tela de login
        }
    }
}
