package com.example.flood.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flood.App
import com.example.flood.R
import com.google.android.material.textfield.TextInputEditText
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch


    class SignupFragment : Fragment(R.layout.signup) {

        private lateinit var emailInput: TextInputEditText
        private lateinit var passwordInput: TextInputEditText
        private lateinit var signupButton: Button
        private lateinit var loginText: TextView
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            emailInput = view.findViewById(R.id.editText_email_signup)
            passwordInput = view.findViewById(R.id.editText_password_signup)
            signupButton = view.findViewById(R.id.button_signup)
            loginText = view.findViewById(R.id.textView_login)
            val supabase = (requireActivity().application as App).supabase

            signupButton.setOnClickListener {
                val email = emailInput.text.toString().trim()
                val password = passwordInput.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                lifecycleScope.launch {
                    try {
                        val result = supabase.gotrue.signUpWith(Email) {
                            this.email = email
                            this.password = password
                        }
                        Toast.makeText(requireContext(), "Signup successful! Please verify your email.", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Signup failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }