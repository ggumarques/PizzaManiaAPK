package com.example.appdepizzaria

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class ThankYouScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you_screen)

        window.statusBarColor = Color.parseColor("#E0E0E0")
    }
}