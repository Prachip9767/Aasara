package com.app.aasara

data class slider ( private var imgUrl: String? = null){
    // Constructor method.
    fun SliderData(imgUrl: String?) {
        this.imgUrl = imgUrl
    }

    // Getter method
    fun getImgUrl(): String? {
        return imgUrl
    }

    // Setter method
    fun setImgUrl(imgUrl: String?) {
        this.imgUrl = imgUrl
    }
}




