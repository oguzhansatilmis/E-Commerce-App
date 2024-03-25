package com.android.ecommerceapp.ui.electronics

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.android.ecommerceapp.MainActivity
import com.android.ecommerceapp.R
import com.android.ecommerceapp.base.BaseFragment
import com.android.ecommerceapp.databinding.FragmentElectronicsBinding
import com.android.ecommerceapp.model.ProductItem
import com.android.ecommerceapp.model.Result
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ElectronicsFragment :
    BaseFragment<FragmentElectronicsBinding, ElectronicsViewModel, MainActivity>(
        FragmentElectronicsBinding::inflate
    ) {
    override val viewModel by viewModels<ElectronicsViewModel>()

    private val tabTitles = arrayListOf("TAB1","TAB2","TAB3")
    private lateinit var adapter: CategoryAdapter


    override fun onCreateFinished() {

        val args by navArgs<ElectronicsFragmentArgs>()
        println(args.categoryArgument)
        viewModel.getCategoryItems(args.categoryArgument)


    }

    override fun initializeListeners() {
       // setupTabLayoutWithViewPager()
    }

    override fun observeEvents() {


        viewModel.categoryLiveData.observe(this) {

            when (it) {

                is Result.Success -> {

                    activity().hideProgress()

                    it.data?.let { response ->

                        response.body()?.let { item ->

                            setupAdapter(item)
                        }
                    }
                }

                is Result.Loading -> {
                    activity().showProgress()
                }

                is Result.Error -> {
                    activity().hideProgress()
                }
            }
        }
    }

    private fun setupAdapter(categoryList: List<ProductItem>) {

        adapter = CategoryAdapter(categoryList)

        binding.apply {
            recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerview.adapter = adapter
        }
    }


    /*
      @SuppressLint("InflateParams")
      private fun setupTabLayoutWithViewPager(){
       binding.viewpager.adapter = PagerAdapter(this)
       TabLayoutMediator(binding.tabLayout,binding.viewpager){ tab, position->
           tab.text = tabTitles[position]

       }.attach()

       for (i in 0..2){

           val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title,null) as TextView

           binding.tabLayout.getTabAt(1)?.customView = textView
       }
   }
     */




}