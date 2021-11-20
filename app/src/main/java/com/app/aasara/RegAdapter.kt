package com.app.aasara

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegAdapter(private val regestrationFragment: RegestrationFragment,
                 private val reglist:MutableList<RegModel>,
                 private val onEventItemClick: RegestrationFragment
) :
    RecyclerView.Adapter<RegAdapter.RegViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegAdapter.RegViewHolder{
        val view: View =
        LayoutInflater.from(parent.context).inflate(R.layout.item_reg, parent, false)
        return RegAdapter.RegViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: RegAdapter.RegViewHolder, position: Int) {
        val regi = reglist[position]
       holder.ngoname.text=regi.ngoName
        holder.ngoyear.text = regi.year
        holder.ngoAdd.text = regi.address
        holder.ngoAdd.text = regi.adhar
        holder.ngoDriving.text = regi.driving
        holder.ngophone.text = regi.contact
        holder.ngopassport.text=regi.passport
        holder.ngovoter.text=regi.voter
//        holder.img.setImageResource(R.id.ivbill)
        holder.edit.setOnClickListener {
            onEventItemClick.OnRegDetlete(regi)
        }
    }

    override fun getItemCount(): Int {
        return reglist.size
    }
    class RegViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var ngoname: TextView = itemView.findViewById(R.id.ngo_name)
        var ngoyear: TextView = itemView.findViewById(R.id.ngo_year)
        var ngoAdd: TextView = itemView.findViewById(R.id.ngo_add)
        var ngoAdhar: TextView = itemView.findViewById(R.id.adhar_num)
        var ngoDriving: TextView = itemView.findViewById(R.id.licence_ngo)
        var ngophone: TextView = itemView.findViewById(R.id.ngo_contact)
        var ngopassport: TextView = itemView.findViewById(R.id.ngo_passport)
        var ngovoter: TextView = itemView.findViewById(R.id.ngo_voter)
//        var img : ImageView=itemView.findViewById(R.id.ivbill)
        var edit: ImageView = itemView.findViewById(R.id.ngo_edit)
        var delete: ImageView = itemView.findViewById(R.id.ngo_delete)
    }
}