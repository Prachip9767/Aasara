package com.app.aasara.sellingDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.aasara.R
import com.app.aasara.commanInBoth.MainUIModel
import com.app.aasara.ngoDetail.NgoDetail
import com.app.aasara.ngoDetail.NgoDetailsAdapter
import com.app.aasara.ngoDetail.NgoViewModel
import kotlinx.android.synthetic.main.activity_ngo_details.*
import kotlinx.android.synthetic.main.activity_selling.*

class SellingActivity : AppCompatActivity() {

    private lateinit var viewModel: SellingViewModel
    private var list= ArrayList<SellingDetail>()
    private lateinit var sellingAdapter: SellingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selling)

        viewModel= ViewModelProvider(this).get(SellingViewModel::class.java)
        viewModel.callApi()

        viewModel.liveData.observe(this,{
            it.let {
                when(it){
                    is MainUIModel.onSellingSuccess ->{
                        list =it.sellingResponse.SellingDetails as ArrayList<SellingDetail>
                        setRecyclerView()
                    }
                    is MainUIModel.onFailure ->{
                        Toast.makeText(this@SellingActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setRecyclerView() {
       sellingAdapter= SellingAdapter(list)
        val linearLayoutManager= LinearLayoutManager(this)
        sellingRecyclerView.apply {
            adapter=sellingAdapter
            layoutManager=linearLayoutManager
        }
    }
}