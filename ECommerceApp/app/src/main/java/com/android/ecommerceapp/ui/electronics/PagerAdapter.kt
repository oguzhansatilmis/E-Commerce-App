package com.android.ecommerceapp.ui.electronics

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.ecommerceapp.ui.home.HomeFragment
import com.android.ecommerceapp.ui.product.ProductFragment

class PagerAdapter(fragment:Fragment):FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when(position){

            0-> HomeFragment()
            1-> ProductFragment()
            2-> HomeFragment()

            else -> {HomeFragment()}
        }
    }

}