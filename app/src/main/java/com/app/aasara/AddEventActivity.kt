package com.app.aasara

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddEventActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var desc: EditText
    private lateinit var category: EditText
    private lateinit var date: EditText
    private lateinit var location: EditText
    private lateinit var duration: EditText
    private lateinit var createEvent: Button
    private var eventsList: MutableList<EventModel> = mutableListOf()
    private lateinit var eventAdapter: EventAdapter

    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        initViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {
        name = findViewById(R.id.et_name)
        desc = findViewById(R.id.et_desc)
        category = findViewById(R.id.et_category)
        date = findViewById(R.id.et_date)
        location = findViewById(R.id.et_location)
        duration = findViewById(R.id.et_duration)
        createEvent = findViewById(R.id.createEvent)

        createEvent.setOnClickListener {
            val name = name.text.toString()
            val desc = desc.text.toString()
            val category = category.text.toString()
            val date = date.text.toString()
            val location = location.text.toString()
            val duration = duration.text.toString()
            // Create a new event
            val event = hashMapOf(
                "name" to name,
                "desc" to desc,
                "category" to category,
                "date" to date,
                "location" to location,
                "duration" to duration
            )
            // Add a new document with a generated ID
            db.collection("events")
                .add(event)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Event created successfully!", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            eventsList.clear()
            eventAdapter.notifyDataSetChanged()
            val intent = Intent(this, EventsFragment::class.java)
            intent.putExtra("name", name)
            intent.putExtra("desc", desc)
            intent.putExtra("category", category)
            intent.putExtra("date", date)
            intent.putExtra("location", location)
            intent.putExtra("duration", duration)
            startActivity(intent)
        }
    }
}