package com.example.flood.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.flood.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLink(view.findViewById(R.id.link1), "https://ndma.gov.in/")
        setLink(view.findViewById(R.id.link2), "https://reliefweb.int/disasters")
        setLink(view.findViewById(R.id.link3), "https://www.imd.gov.in/")
        setLink(view.findViewById(R.id.link4), "https://floodlist.com/")
        setLink(view.findViewById(R.id.link5), "https://timesofindia.indiatimes.com/topic/flood")
    }

    private fun setLink(textView: TextView, url: String) {
        textView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}

private fun TextView.setOnClickListener(function: () -> Unit) {
    TODO("Not yet implemented")
}
