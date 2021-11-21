package com.app.aasara.ngoDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.aasara.R
import com.bumptech.glide.Glide

class NgoDetailsAdapter(var list:ArrayList<NgoDetail>,var listner:FullShow): RecyclerView.Adapter<NgoDetailsAdapter.NgoViewHolder>() {

     var expandable: Expandable = Expandable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NgoViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.ngo_details_layout,parent,false)
        return NgoViewHolder(view)
    }

    override fun onBindViewHolder(holder: NgoViewHolder, position: Int) {
        var ngoList=list[position]

        holder.setData(ngoList)

        var isExpandable:Boolean =expandable.getExpandable()
        holder.expandLayout.visibility=if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
          expandable.setExpandable(!isExpandable)
          notifyItemChanged(position)
        }

        holder.linearLayout.setOnLongClickListener {
            listner.showFullDetails(position)
            return@setOnLongClickListener true

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class NgoViewHolder(item:View):RecyclerView.ViewHolder(item){
        var ngoName:TextView=item.findViewById(R.id.ngoName)
        var ngoAddress:TextView=item.findViewById(R.id.ngoAddress)
        var ngoAreaOfWork:TextView=item.findViewById(R.id.ngoAreaOfWork)
        var ngoWebsite:TextView=item.findViewById(R.id.ngoWebsite)
        var ngoContact:TextView=item.findViewById(R.id.ngoContact)
      //  var description:TextView=item.findViewById(R.id.ngoDescription)
        var linearLayout:LinearLayout=item.findViewById(R.id.linearLayout)
        var expandLayout:ConstraintLayout=item.findViewById(R.id.expandableLayout)
        var image:ImageView=item.findViewById(R.id.ngoImage)

        fun setData(list: NgoDetail){
           try {
               ngoName.text=list.NgoName
               Glide.with(image).load(list.Images).into(image)
               ngoContact.text="Contact- ${list.ContactDetails}"
              // description.text="Description - ${list.NgoDescription}"
               ngoAddress.text="Address - ${list.Address}"
               ngoAreaOfWork.text="Area of Work - ${list.AreaOfWork}"
               ngoWebsite.text="Website- ${list.Website}"

           }catch (e:Exception){
               e.message
           }
       }
    }

}