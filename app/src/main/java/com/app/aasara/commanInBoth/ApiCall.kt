package com.app.aasara.commanInBoth

import com.app.aasara.ngoDetail.ResponseModel
import com.app.aasara.sellingDetails.SellingResponse
import retrofit2.http.GET


interface ApiCall {

    @GET("858cc63c-3880-432c-b11f-de8a0e2a571e")
    suspend fun getAllCharacters(): ResponseModel

    @GET("2b0da1c2-6932-4333-859c-dea1bcc4eabf")
    suspend fun getSelling(): SellingResponse


}