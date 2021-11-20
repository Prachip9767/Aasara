package com.app.aasara

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_reg.*

class RegistrationApplication : AppCompatActivity() {
    private lateinit var nname: EditText
    private lateinit var nyear: EditText
    private lateinit var naddress: EditText
    private lateinit var nadhar: EditText
    private lateinit var ndriving: EditText
    private lateinit var ncontact: EditText
    private lateinit var npassport: EditText
    private lateinit var nVoter:EditText
//    private lateinit var nivbill:ImageView
    private lateinit var regis:Button
    private var regssList: MutableList<RegModel> = mutableListOf()
    private lateinit var regAdapter: RegAdapter

    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_application)
        initViews()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {
       nname = findViewById(R.id.etNameof)
        naddress = findViewById(R.id.etaddof)
        nyear = findViewById(R.id.etstart)
        nadhar = findViewById(R.id.etcityof)
        ndriving = findViewById(R.id.etStateof)
        ncontact = findViewById(R.id.etPhoneof)
        npassport = findViewById(R.id.etEmailof)
        nVoter = findViewById(R.id.evvoter)
//        nivbill=findViewById(R.id.ivbill)
        regis=findViewById(R.id.btnreg)


        regis.setOnClickListener {
            val name = nname.text.toString()
            val desc = nyear.text.toString()
            val category = naddress.text.toString()
            val date = nadhar.text.toString()
            val location = ndriving.text.toString()
            val duration = ncontact.text.toString()
            val passport=npassport.text.toString()
            val voterid= nVoter.text.toString()
//            val ivBill=nivbill.toString()
            // Create a new event
            val regies = hashMapOf(
                "name" to name,
                "desc" to desc,
                "category" to category,
                "date" to date,
                "location" to location,
                "duration" to duration,
            "passport" to passport,
                "voterid" to voterid,
//            "imgbill" to ivBill

            )
            // Add a new document with a generated ID
            db.collection("regies")
                .add(regies)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Event created successfully!", Toast.LENGTH_SHORT).show()
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

            regssList.clear()
           regAdapter.notifyDataSetChanged()
            val intent = Intent(this, EventsFragment::class.java)
            intent.putExtra("name", name)
            intent.putExtra("desc", desc)
            intent.putExtra("category", category)
            intent.putExtra("date", date)
            intent.putExtra("location", location)
            intent.putExtra("duration", duration)
            intent.putExtra("passport", passport)
            intent.putExtra("voterid", voterid)
//            intent.putExtra("imgbill", ivBill)
            startActivity(intent)
        }
    }
}