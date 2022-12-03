package com.example.homework.shopDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.homework.R
import com.example.homework.shoplist.ShopID

/**
 * 商店详情
 *
 */
class ShopDetail_activity : AppCompatActivity() {
    private val shopDetailViewModel by viewModels<ShopDetailViewModel>{
        ShopDetailViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        var currentShopId: Long? = null

        val bundle:Bundle? = intent.extras
        if(bundle != null){
            currentShopId = bundle.getLong(ShopID)
            Toast.makeText(applicationContext,currentShopId.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}