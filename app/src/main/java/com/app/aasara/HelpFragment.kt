package com.app.aasara

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class HelpFragment : Fragment() {

    private lateinit var bloodDonation: ImageView
    private lateinit var organDonation: ImageView
    private lateinit var covidVaccination: ImageView
    private lateinit var shareMeal: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bloodDonation = view.findViewById(R.id.blood_donation)
        bloodDonation.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", "http://nbtc.naco.gov.in/bloodBank/findblood")
            startActivity(intent)
        }
        organDonation = view.findViewById(R.id.organ_donation)
        organDonation.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", "https://www.organindia.org/claim-your-donor-card/")
            startActivity(intent)
        }
        covidVaccination = view.findViewById(R.id.covid_vaccination)
        covidVaccination.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", "https://www.indianredcross.org/ircs/COVID19Donate")
            startActivity(intent)
        }
        shareMeal = view.findViewById(R.id.share_meal)
        shareMeal.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", "https://www.akshayapatra.org/onlinedonations")
            startActivity(intent)
        }
    }
}