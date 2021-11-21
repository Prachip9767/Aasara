package com.app.aasara.commanInBoth

import com.app.aasara.ngoDetail.ResponseModel
import com.app.aasara.sellingDetails.SellingResponse

sealed class MainUIModel{

    data class onSuccess(val responseModel: ResponseModel): MainUIModel()

    data class onSellingSuccess(val sellingResponse: SellingResponse): MainUIModel()

    data class onFailure(val error:String): MainUIModel()
}
