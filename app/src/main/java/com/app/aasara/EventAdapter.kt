package com.app.aasara

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(
    private var eventsFragment: EventsFragment,
    private val eventsList: MutableList<EventModel>,
    private var listener: OnEventItemClick
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.event_item_layout, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventsList[position]
        holder.name.text = event.name
        holder.desc.text = event.desc
        holder.category.text = event.category
        holder.date.text = event.date
        holder.location.text = event.location
        holder.duration.text = event.duration
        holder.edit.setOnClickListener {
            listener.onEditClicked(event)
        }
        holder.delete.setOnClickListener {
            listener.onDeleteClicked(event)
        }
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.event_name)
        var desc: TextView = itemView.findViewById(R.id.event_desc)
        var category: TextView = itemView.findViewById(R.id.event_category)
        var date: TextView = itemView.findViewById(R.id.event_date)
        var location: TextView = itemView.findViewById(R.id.event_location)
        var duration: TextView = itemView.findViewById(R.id.event_duration)
        var edit: ImageView = itemView.findViewById(R.id.event_edit)
        var delete: ImageView = itemView.findViewById(R.id.event_delete)
    }
}