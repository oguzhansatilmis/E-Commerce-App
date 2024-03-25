package com.android.ecommerceapp.ui.jewelery

import androidx.fragment.app.viewModels
import com.android.ecommerceapp.MainActivity
import com.android.ecommerceapp.base.BaseFragment
import com.android.ecommerceapp.databinding.FragmentJeweleryBinding


class JeweleryFragment : BaseFragment<FragmentJeweleryBinding,JeweleryViewModel,MainActivity>(FragmentJeweleryBinding::inflate) {

    override val viewModel by viewModels<JeweleryViewModel>()
    override fun onCreateFinished() {

    }

    override fun initializeListeners() {
        TODO("Not yet implemented")
    }

    override fun observeEvents() {
        TODO("Not yet implemented")
    }


}