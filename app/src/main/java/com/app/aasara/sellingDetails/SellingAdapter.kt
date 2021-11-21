package com.app.aasara.sellingDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.aasara.R
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class SellingAdapter(var list:List<SellingDetail>) :RecyclerView.Adapter<SellingAdapter.SellingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellingViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.selling_layout,parent,false)
        return SellingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SellingViewHolder, position: Int) {
        var sellingList=list[position]
        holder.setData(sellingList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class SellingViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var productImage:ImageView=itemView.findViewById(R.id.productImage)
        var productName:TextView=itemView.findViewById(R.id.productName)
        var productPrice:TextView=itemView.findViewById(R.id.productPrice)
        var productRating:TextView=itemView.findViewById(R.id.productRating)

        fun setData(sellingDetail: SellingDetail){
            Glide.with(productImage).load(sellingDetail.Images).into(productImage)
            productName.text=sellingDetail.Name
            productPrice.text="Price- ${sellingDetail.Price}"
            productRating.text="Rating - ${sellingDetail.Rating}"
        }

    }
}