package com.app.aasara

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sliders.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR;
        sliders.scrollTimeInSec = 3;
        sliders.isAutoCycle = true;
        sliders.startAutoCycle();
    }
}
