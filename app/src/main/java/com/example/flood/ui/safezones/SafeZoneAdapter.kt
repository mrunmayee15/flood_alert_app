package com.example.flood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flood.R
import com.example.flood.model.SafeZone

class SafeZoneAdapter(private val zones: List<SafeZone>) :
    RecyclerView.Adapter<SafeZoneAdapter.SafeZoneViewHolder>() {

    class SafeZoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.safezoneName)
        val address: TextView = itemView.findViewById(R.id.safezoneAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SafeZoneViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_safezone, parent, false)
        return SafeZoneViewHolder(view)
    }

    override fun onBindViewHolder(holder: SafeZoneViewHolder, position: Int) {
        val zone = zones[position]
        holder.name.text = zone.name
        holder.address.text = zone.address
    }

    override fun getItemCount() = zones.size
}
