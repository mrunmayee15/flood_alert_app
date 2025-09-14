package com.example.flood.safezone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flood.App
import com.example.flood.R
import com.example.flood.adapter.SafeZoneAdapter
import com.example.flood.model.SafeZone
import io.github.jan.supabase.postgrest.postgrest

import kotlinx.coroutines.launch

class SafeZoneFragment : Fragment(R.layout.fragment_safezone) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SafeZoneAdapter
    private val zones = mutableListOf<SafeZone>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_safezone, container, false)

        recyclerView = view.findViewById(R.id.safezonesRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SafeZoneAdapter(zones)
        recyclerView.adapter = adapter

        fetchSafeZones()

        return view
    }

    private fun fetchSafeZones() {
        lifecycleScope.launch {
            try {
                val client = (requireActivity().application as App).supabase
                val result = client.postgrest["safe_zones"].select().decodeList<SafeZone>()
                Log.d("SafeZones", "Raw response: ${result}") //
                zones.clear()
                zones.addAll(result)
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Log.e("SafeZones", "Error fetching: ${e.message}")
                Toast.makeText(requireContext(), "Failed to load safe zones", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
