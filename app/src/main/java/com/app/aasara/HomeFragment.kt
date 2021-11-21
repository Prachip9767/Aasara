package com.app.aasara

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.slider_image.*

class HomeFragment : Fragment(R.layout.fragment_home) {
  private lateinit var sliderView: SliderView;
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val imageList: ArrayList<String> = ArrayList()
//        imageList.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
//        imageList.add("https://images.ctfassets.net/hrltx12pl8hq/4plHDVeTkWuFMihxQnzBSb/aea2f06d675c3d710d095306e377382f/shutterstock_554314555_copy.jpg")
//        imageList.add("https://media.istockphoto.com/photos/child-hands-formig-heart-shape-picture-id951945718?k=6&m=951945718&s=612x612&w=0&h=ih-N7RytxrTfhDyvyTQCA5q5xKoJToKSYgdsJ_mHrv0=")
//        setImageInSlider(imageList, imageSlider = sliderView)
    }

//    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
//        val adapter = MySliderImageAdapter()
//        adapter.renewItems(images)
//        imageSlider.setSliderAdapter(adapter)
//        imageSlider.isAutoCycle = true
//        imageSlider.startAutoCycle()
//    }
}

