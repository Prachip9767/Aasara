package com.app.Aasara

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditEventActivity : AppCompatActivity() {

    private val etName: EditText = findViewById(R.id.et_event_name)
    private val etDesc: EditText = findViewById(R.id.et_event_desc)
    private val etCategory: EditText = findViewById(R.id.et_event_category)
    private val etDate: EditText = findViewById(R.id.et_event_date)
    private val etLocation: EditText = findViewById(R.id.et_event_location)
    private val etDuration: EditText = findViewById(R.id.et_event_duration)
    private val updateEvent: Button = findViewById(R.id.updateEvent)
    private var eventsList: MutableList<EventModel> = mutableListOf()
    private lateinit var eventAdapter: EventAdapter
    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_event)

        val id: String? = intent.getStringExtra("id")
        val name: String? = intent.getStringExtra("name")
        val desc: String? = intent.getStringExtra("desc")
        val category: String? = intent.getStringExtra("category")
        val date: String? = intent.getStringExtra("date")
        val location: String? = intent.getStringExtra("location")
        val duration: String? = intent.getStringExtra("duration")

        etName.setText(name)
        etDesc.setText(desc)
        etCategory.setText(category)
        etDate.setText(date)
        etLocation.setText(location)
        etDuration.setText(duration)

        updateEvent.setOnClickListener {
            val finalName = etName.text.toString()
            val finalDesc = etDesc.text.toString()
            val finalCategory = etCategory.text.toString()
            val finalDate = etDate.text.toString()
            val finalLocation = etLocation.text.toString()
            val finalDuration = etDuration.text.toString()

            eventsList.clear()
            eventAdapter.notifyDataSetChanged()

            val intent = Intent(this, EventsFragment::class.java)
            startActivity(intent)
        }
    }
}