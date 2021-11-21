package com.app.aasara.commanInBoth

import com.app.aasara.ngoDetail.ResponseModel
import com.app.aasara.sellingDetails.SellingResponse

class MainRepository {

    suspend fun getAllCharacters(): ResponseModel {
        return Network.getApiService().getAllCharacters()
    }

    suspend fun getSelling(): SellingResponse {
        return Network.getApiService().getSelling()
    }
}