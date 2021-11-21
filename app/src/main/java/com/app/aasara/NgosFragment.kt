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
import com.app.aasara.ngoDetail.NgoDetail
import com.app.aasara.ngoDetail.NgoDetailsAdapter
import com.app.aasara.ngoDetail.NgoViewModel
import kotlinx.android.synthetic.main.activity_ngo_details.*

class NgosFragment : Fragment(R.layout.fragment_ngos) {
    private lateinit var viewModel: NgoViewModel
    private var list= ArrayList<NgoDetail>()
    private lateinit var ngoAdapter : NgoDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(NgoViewModel::class.java)
        viewModel.callApi()

        viewModel.liveData.observe(viewLifecycleOwner,{
            it.let {
                when(it){
                    is MainUIModel.onSuccess ->{
                        list =it.responseModel.NgoDetails as ArrayList<NgoDetail>
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
        ngoAdapter= NgoDetailsAdapter(list)
        val linearLayoutManager= LinearLayoutManager(context)
        recyclerView.apply {
            adapter=ngoAdapter
            layoutManager=linearLayoutManager
        }
    }
}