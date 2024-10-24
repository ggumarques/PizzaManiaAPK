package com.example.appdepizzaria

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.appdepizzaria.databinding.ActivityThankYouScreenBinding

class ThankYouScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThankYouScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThankYouScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        binding.btNewOrder.setOnClickListener {
            // Voltar para a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Finaliza a tela de agradecimento para não voltar a ela
        }
    }
}
