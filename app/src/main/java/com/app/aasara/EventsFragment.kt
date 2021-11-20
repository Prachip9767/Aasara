package com.app.aasara

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EventsFragment : Fragment(), OnEventItemClick {

    private lateinit var addEvent: LinearLayout
    private lateinit var eventAdapter: EventAdapter
    private lateinit var recyclerView: RecyclerView
    private var eventsList: MutableList<EventModel> = mutableListOf()
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addEvent = view.findViewById(R.id.addEvent)

        recyclerView = view.findViewById(R.id.eventsRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        eventAdapter = EventAdapter(this, eventsList, this)
        recyclerView.adapter = eventAdapter
        fetchData()

        addEvent.setOnClickListener {
            val intent = Intent(context, AddEventActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData() {
        db.collection("events")
            .get()
            .addOnCompleteListener {
                eventsList.clear()
                for (snapshot: DocumentSnapshot in it.result!!) {
                    val eventModel =
                        EventModel(
                            snapshot.getString("id"),
                            snapshot.getString("name"),
                            snapshot.getString("desc"),
                            snapshot.getString("category"),
                            snapshot.getString("date"),
                            snapshot.getString("location"),
                            snapshot.getString("duration")
                        )
                    eventsList.add(eventModel)
                }
                eventAdapter.notifyDataSetChanged()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to update events!", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onEditClicked(model: EventModel) {
        val intent = Intent(context, EditEventActivity::class.java)
        intent.putExtra("id", model.id)
        intent.putExtra("name", model.name)
        intent.putExtra("desc", model.desc)
        intent.putExtra("category", model.category)
        intent.putExtra("date", model.date)
        intent.putExtra("location", model.location)
        intent.putExtra("price", model.duration)
        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDeleteClicked(model: EventModel) {
        eventsList.clear()
        eventAdapter.notifyDataSetChanged()
    }
}