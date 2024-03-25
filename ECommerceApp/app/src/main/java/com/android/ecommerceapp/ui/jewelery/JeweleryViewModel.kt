package com.android.ecommerceapp.ui.jewelery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ecommerceapp.model.ProductItem
import com.android.ecommerceapp.model.Result
import com.android.ecommerceapp.repository.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JeweleryViewModel @Inject constructor(
    private val repository: CommerceRepository
) : ViewModel() {

    private val _jeweleryResponseState = MutableStateFlow<Result<Response<List<ProductItem>>>>(Result.Loading())
    val jeweleryResponseState: StateFlow<Result<Response<List<ProductItem>>>> = _jeweleryResponseState

     fun getJeweleryList(jewelery:String){

         viewModelScope.launch {
             val jeweleryList = repository.getCategoryItems(jewelery)

             jeweleryList?.let { response->

                 if (jeweleryList.isSuccessful){
                     _jeweleryResponseState.value = Result.Success(response)
                 }
                 else{
                     _jeweleryResponseState.value = Result.Error("Error Message",response)
                 }

             }

         }

    }
}