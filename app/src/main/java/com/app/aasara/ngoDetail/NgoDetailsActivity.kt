package com.app.aasara.ngoDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.aasara.R
import com.app.aasara.commanInBoth.MainUIModel
import kotlinx.android.synthetic.main.activity_ngo_details.*

class NgoDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: NgoViewModel
    private var list= ArrayList<NgoDetail>()
    private lateinit var ngoAdapter : NgoDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_details)

        viewModel= ViewModelProvider(this).get(NgoViewModel::class.java)
        viewModel.callApi()

        viewModel.liveData.observe(this,{
            it.let {
                when(it){
                    is MainUIModel.onSuccess ->{
                        list =it.responseModel.NgoDetails as ArrayList<NgoDetail>
                        setRecyclerView()
                    }
                    is MainUIModel.onFailure ->{
                        Toast.makeText(this@NgoDetailsActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setRecyclerView() {
       ngoAdapter= NgoDetailsAdapter(list)
        val linearLayoutManager= LinearLayoutManager(this)
       recyclerView.apply {
           adapter=ngoAdapter
           layoutManager=linearLayoutManager
       }
    }
}