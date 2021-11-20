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

class RegestrationFragment : Fragment(),OnEventItemClick {

    private lateinit var addReg: LinearLayout
    private lateinit var regAdapter: RegAdapter
    private lateinit var recyclerView: RecyclerView
    private var reglist: MutableList<RegModel> = mutableListOf()
    private val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regestration, container, false)
    }

    private lateinit var uid: String
    private lateinit var uname: String
    private lateinit var udesc: String
    private lateinit var ucategory: String
    private lateinit var udate: String
    private lateinit var ulocation: String
    private lateinit var upassport: String
    private lateinit var uvoter: String
//    private lateinit var uimage: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addReg = view.findViewById(R.id.regTool)

        recyclerView = view.findViewById(R.id.eventsRecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        regAdapter = RegAdapter(this, reglist, this)
        recyclerView.adapter = regAdapter
        fetchData()

        addReg.setOnClickListener {
            val intent = Intent(context, AddEventActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData() {
        db.collection("regies")
            .get()
            .addOnCompleteListener {
                reglist.clear()
                for (snapshot: DocumentSnapshot in it.result!!) {
                    val regModel =
                        RegModel(
                            snapshot.getString("id"),
                            snapshot.getString("name"),
                            snapshot.getString("desc"),
                            snapshot.getString("category"),
                            snapshot.getString("date"),
                            snapshot.getString("location"),
                            snapshot.getString("duration"),
                            snapshot.getString("passport"),
                            snapshot.getString("voterid"),
//                            snapshot.get("imgbill") as Int
                        )
                    reglist.add(regModel)
                }
                regAdapter.notifyDataSetChanged()
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to update events!", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onEditClicked(model: EventModel) {

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDeleteClicked(model: EventModel) {

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun OnRegDetlete(model: RegModel) {
        reglist.clear()
        regAdapter.notifyDataSetChanged()
    }
}

