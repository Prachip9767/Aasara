package com.app.aasara.ngoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.aasara.commanInBoth.MainRepository
import com.app.aasara.commanInBoth.MainUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NgoViewModel() :ViewModel() {

    private val mainRespositry= MainRepository()
    private val mutableLiveData= MutableLiveData<MainUIModel>()

    val liveData: LiveData<MainUIModel> = mutableLiveData

    fun callApi(){
        CoroutineScope(Dispatchers.IO).launch {
            val response: ResponseModel =mainRespositry.getAllCharacters()
            //mutableLiveData.postValue(response)
            if (response.NgoDetails!=null){

                mutableLiveData.postValue(MainUIModel.onSuccess(response))

            }else{
                mutableLiveData.postValue(MainUIModel.onFailure("Error"))
            }
        }
    }

}