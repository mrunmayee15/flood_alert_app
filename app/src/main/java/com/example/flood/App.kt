package com.example.flood

import android.app.Application
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

class App : Application() {

    lateinit var supabase: SupabaseClient

    override fun onCreate() {
        super.onCreate()

        supabase = createSupabaseClient(
            supabaseUrl = "https://elfiazumpbcionrptqbu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVsZmlhenVtcGJjaW9ucnB0cWJ1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTc4NTYyMTEsImV4cCI6MjA3MzQzMjIxMX0.cMmXB6Ku8Sm5dH_8Xy3NAurIRYJ9EQJ1cDe60zaDtYg"
        ) {
            install(GoTrue)
            install(Postgrest)
        }
    }
}