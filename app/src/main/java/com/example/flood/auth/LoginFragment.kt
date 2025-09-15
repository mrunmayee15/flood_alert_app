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
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.login) {

    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var signUpText: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailInput = view.findViewById(R.id.editText_email)
        passwordInput = view.findViewById(R.id.editText_password)
        loginButton = view.findViewById(R.id.button_login)
        signUpText = view.findViewById(R.id.button_signup)
        signUpText.apply {
            isClickable = true
            isFocusable = true
            setOnClickListener {
//                Toast.makeText(requireContext(), "Sign up clicked", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
        }
        val supabase = (requireActivity().application as App).supabase

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                try {
                    val result = supabase.gotrue.loginWith(Email) {
                        this.email = email
                        this.password = password
                    }
                    if(result!= null) {
                        Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.nav_home)
                    }// Navigate to home fragment
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

//        signUpText.setOnClickListener {
//            Toast.makeText(requireContext(), "Sign up clicked", Toast.LENGTH_SHORT).show()
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.nav_signup, SignupFragment())
//                .addToBackStack(null)
//                .commit()
//        }

    }
}
