package com.example.homework.shoplist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework.data.DataSource

class ShopsListViewModel(val dataSource: DataSource) : ViewModel() {
    val shopsLiveData = dataSource.getShopList()
}

class ShopsListViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ShopsListViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ShopsListViewModel(
                dataSource =  DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}