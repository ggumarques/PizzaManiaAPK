package com.example.appdepizzaria

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appdepizzaria.databinding.ActivityPaymentBinding
import java.text.DecimalFormat

class Payment : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        val name = intent.extras?.getString("name") ?: ""
        val amount = intent.extras?.getInt("amount") ?: 0
        val total = intent.extras?.getDouble("total") ?: 0.0
        val sauceAndDrinks = intent.extras?.getString("saucesAndDrinks") ?: ""
        val decimalFormat = DecimalFormat.getCurrencyInstance()

        binding.txtTotal.text = "$name \n Amount: $amount \n Sauces And Drinks: $sauceAndDrinks \n Total: ${decimalFormat.format(total)}"

        binding.btPay.setOnClickListener {
            when {
                binding.btCreditCard.isChecked -> {

                    binding.qrCodeImage.visibility = View.GONE
                    val intent = Intent(this, ThankYouScreen::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Card Payment", Toast.LENGTH_SHORT).show()
                }
                binding.btPix.isChecked -> {

                    binding.qrCodeImage.visibility = View.VISIBLE
                    Toast.makeText(this, "Generating QR Code for Pix Payment", Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, ThankYouScreen::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Payment with Pix", Toast.LENGTH_SHORT).show()
                    }, 10000)
                }
                else -> {
                    Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
