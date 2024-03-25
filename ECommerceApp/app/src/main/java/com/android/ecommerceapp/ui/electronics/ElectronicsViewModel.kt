package com.android.ecommerceapp.ui.electronics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ecommerceapp.model.ProductItem

import com.android.ecommerceapp.model.Result
import com.android.ecommerceapp.repository.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class ElectronicsViewModel @Inject constructor(
    private val repository: CommerceRepository
) : ViewModel() {

    private val _categoryLiveData = MutableLiveData<Result<Response<List<ProductItem>>>>(Result.Loading())
    val categoryLiveData: LiveData<Result<Response<List<ProductItem>>>> = _categoryLiveData

     fun getCategoryItems(category:String){

        viewModelScope.launch {

             val categoryItems=  repository.getCategoryItems(category)


            categoryItems?.let { response->

                if (response.isSuccessful){

                    _categoryLiveData.value = Result.Success(response)
                }
                else{
                    _categoryLiveData.value = Result.Error("Error message",response)
                }

            }

        }

    }

}