package com.android.ecommerceapp.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.android.ecommerceapp.MainActivity
import com.android.ecommerceapp.R
import com.android.ecommerceapp.base.BaseFragment
import com.android.ecommerceapp.databinding.FragmentHomeBinding
import com.android.ecommerceapp.model.Result
import com.android.ecommerceapp.model.enums.MessageType
import com.android.ecommerceapp.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, MainActivity>(FragmentHomeBinding::inflate) {
    override val viewModel by viewModels<HomeViewModel>()


    override fun onCreateFinished() {

        setupCategoryImage()
    }

    override fun initializeListeners() {

        binding.electronicsImage.setOnClickListener {
            val bundle = Bundle()
            bundle.apply {
                putString("categoryArgument","electronics")
            }
            findNavController().navigate(R.id.action_homeFragment_to_electronicsFragment,bundle)

        }


    }

    override fun observeEvents() {

        observeCategories()

    }

    private fun observeCategories() {
        lifecycleScope.launch {


            viewModel.categoriesResponseState.collect {

                when (it) {

                    is Result.Success -> {

                        activity().hideProgress()
                        val categories = it.data?.body()

                        categories?.forEachIndexed { index, product ->
                            val elementId = arrayListOf(
                                binding.electronicsText,
                                binding.jeweleryText,
                                binding.menClothingText,
                                binding.womenClothingText
                            )
                            elementId[index].text = product
                        }
                    }

                    is Result.Loading -> {
                        activity().showProgress()
                    }

                    is Result.Error -> {

                        activity().showMessage("it.message", MessageType.ERROR)

                    }
                }

            }
        }
    }

    private fun setupCategoryImage() {

        binding.apply {
            menClothingImage.loadUrl("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
            womenClothingImage.loadUrl("https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg")
            electronicsImage.loadUrl("https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg")
            jeweleryImage.loadUrl("https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg")

        }

    }


}