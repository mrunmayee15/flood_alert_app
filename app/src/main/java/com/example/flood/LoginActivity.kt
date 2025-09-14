package com.example.flood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login) // your login.xml

        // Optional: Navigate to MainActivity after successful login
         val loginButton: Button = findViewById(R.id.button_login)
         loginButton.setOnClickListener {
             startActivity(Intent(this, MainActivity::class.java))
             finish() // finish LoginActivity so user can't go back
         }
    }
}
