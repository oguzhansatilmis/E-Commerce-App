package com.android.ecommerceapp.repository

import android.util.Log
import com.android.ecommerceapp.model.Product
import com.android.ecommerceapp.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class CommerceRepository @Inject constructor(
    private val apiService: ApiService
) {
     fun getAllProduct(): Flow<Response<List<Product>>> = flow{

        val productResponse = apiService.getAllProducts()


        if (productResponse.isSuccessful){
            emit(productResponse)
        }
         else{
            Log.e("Repository response error", "${productResponse.code()}")
            emit(productResponse)
        }

    }.catch {
         Log.e("error", "${error("asdsadasdas")}")
     }.flowOn(Dispatchers.IO)

}