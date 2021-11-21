package com.app.aasara

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.aasara.commanInBoth.MainUIModel
import com.app.aasara.sellingDetails.SellingAdapter
import com.app.aasara.sellingDetails.SellingDetail
import com.app.aasara.sellingDetails.SellingViewModel
import kotlinx.android.synthetic.main.activity_selling.*

class StoreFragment : Fragment(R.layout.fragment_store) {

    private lateinit var viewModel: SellingViewModel
    private var list= ArrayList<SellingDetail>()
    private lateinit var sellingAdapter: SellingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(SellingViewModel::class.java)
        viewModel.callApi()

        viewModel.liveData.observe(viewLifecycleOwner,{
            it.let {
                when(it){
                    is MainUIModel.onSellingSuccess ->{
                        list =it.sellingResponse.SellingDetails as ArrayList<SellingDetail>
                        setRecyclerView()
                    }
                    is MainUIModel.onFailure ->{
                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setRecyclerView() {
        sellingAdapter= SellingAdapter(list)
        val linearLayoutManager= LinearLayoutManager(context)
        sellingRecyclerView.apply {
            adapter=sellingAdapter
            layoutManager=linearLayoutManager
        }
    }

}


