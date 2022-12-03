package com.example.homework.shopDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework.data.DataSource
import com.example.homework.data.Shop

class ShopDetailViewModel(private val dataSource: DataSource) : ViewModel() {
    fun getShopId(id: Long) : Shop?{
        return dataSource.getShopId(id)
    }
}

class ShopDetailViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShopDetailViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}