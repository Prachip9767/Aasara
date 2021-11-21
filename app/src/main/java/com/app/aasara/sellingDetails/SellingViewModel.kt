package com.app.aasara.sellingDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.aasara.commanInBoth.MainRepository
import com.app.aasara.commanInBoth.MainUIModel
import com.app.aasara.ngoDetail.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SellingViewModel: ViewModel() {

    private val mainRespositry = MainRepository()
    private val mutableLiveData = MutableLiveData<MainUIModel>()

    val liveData: LiveData<MainUIModel> = mutableLiveData

    fun callApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val sellingResponse:SellingResponse = mainRespositry.getSelling()
            //mutableLiveData.postValue(response)
            if (sellingResponse.SellingDetails != null) {

                mutableLiveData.postValue(MainUIModel.onSellingSuccess(sellingResponse))

            } else {
                mutableLiveData.postValue(MainUIModel.onFailure("Error"))
            }
        }
    }
}