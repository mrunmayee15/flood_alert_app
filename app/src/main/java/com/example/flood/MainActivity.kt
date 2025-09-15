package com.example.flood

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.flood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        val sos = findViewById<LinearLayout>(R.id.bottom_sos)
        val home = findViewById<LinearLayout>(R.id.bottom_home)
        val call = findViewById<LinearLayout>(R.id.bottom_call)

        home.setOnClickListener {
            navController.navigate(R.id.nav_home)
        }

        sos.setOnClickListener {
            val names = arrayOf("Mom", "Dad", "Brother")
            AlertDialog.Builder(this)
                .setTitle("Send SOS to:")
                .setItems(names) { _, which ->
                    val selected = names[which]
                    // TODO: Implement sending SOS message here
                }
                .show()
        }

        call.setOnClickListener {
            val helplines = arrayOf("Flood Helpline: 12345", "Emergency: 112", "Local Police: 100")
            AlertDialog.Builder(this)
                .setTitle("Call Helpline")
                .setItems(helplines) { _, which ->
                    val number = helplines[which].substringAfter(":").trim()
                    // TODO: start phone dialer
                     val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
                     startActivity(intent)
                }
                .show()
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_safezone, R.id.nav_emergencysupply, R.id.nav_notification, R.id.nav_settings, R.id.nav_home, R.id.nav_login
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
