package com.example.homework.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * 获取商店列表
 */
class DataSource(resources: Resources) {
    private val initialShopList = shopList(resources)
    private val shopsLiveData = MutableLiveData(initialShopList)

    fun getShopList():LiveData<List<Shop>>{
        return shopsLiveData
    }
    fun getShopId(id:Long): Shop?{
        shopsLiveData.value?.let {
            shops -> return shops.firstOrNull(){it.id==id}
        }
        return null
    }

    //?
    companion object{
        private var INSTANCE: DataSource? = null
        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}