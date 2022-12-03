package com.example.homework.shoplist


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.data.Shop
import com.example.homework.databinding.ActivityMainBinding
import com.example.homework.login.LoginActivity
import com.example.homework.shopDetail.ShopDetail_activity
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener


const val ShopID = "shop id"


class MainActivity : AppCompatActivity() {

    //保存原有数据
    private val shopListViewModel by viewModels<ShopsListViewModel> {
        ShopsListViewModelFactory(this)
    }

    private lateinit var binding: ActivityMainBinding

    private var clickTime: Long = System.currentTimeMillis()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //获取权限
        getPermission()


        val shopHeaderAdapter = ShopHeaderAdapter()//头部筛选适配器
        val shopsAdapter = ShopsAdapter { shop -> adapterOnClick(shop) }//商店适配器
        val concatAdapter = ConcatAdapter(shopHeaderAdapter, shopsAdapter)//合并筛选和商店适配器
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        recyclerView.adapter = concatAdapter

        //保存原有数据
        shopListViewModel.shopsLiveData.observe(this) {
            it?.let {
                shopsAdapter.submitList(it as MutableList<Shop>)
            }
        }

        val HomePage = binding.HomePage//下方首页图标
        val Indent = binding.indent//下方订单图标
        val My = binding.My//下方我的图标
        val ShowHomePage = binding.showHomePage//首页整体
        val ShowIndent = binding.showIndent//订单页面
        val ShowMy = binding.showMy//我的页面


        //切换首页
        HomePage.setOnClickListener {
            ShowHomePage.visibility = View.VISIBLE
            ShowMy.visibility = View.GONE
            ShowIndent.visibility = View.GONE

            HomePage.setImageResource(R.drawable.home_button2)
            Indent.setImageResource(R.drawable.indent_button1)
            My.setImageResource(R.drawable.my_button1)

        }
        //切换订单
        Indent.setOnClickListener {
            if (check_status()) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                ShowHomePage.visibility = View.GONE
                ShowMy.visibility = View.GONE
                ShowIndent.visibility = View.VISIBLE

                HomePage.setImageResource(R.drawable.home_button1)
                Indent.setImageResource(R.drawable.indent_button2)
                My.setImageResource(R.drawable.my_button1)

            }

        }
        //切换我的
        My.setOnClickListener {
            if (check_status()) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                ShowHomePage.visibility = View.GONE
                ShowMy.visibility = View.VISIBLE
                ShowIndent.visibility = View.GONE

                HomePage.setImageResource(R.drawable.home_button1)
                Indent.setImageResource(R.drawable.indent_button1)
                My.setImageResource(R.drawable.my_button2)

            }

        }


    }

    //定位
    override fun onResume() {
        super.onResume()
        //初始化定位
        locatInit()
    }

    //两次点击back退出程序
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - clickTime > 2000) {
                Toast.makeText(this, "再按一次退出!", Toast.LENGTH_SHORT).show()
                clickTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //索要权限
    override fun onRequestPermissionsResult(
        requestCode: Int,// 请求
        permissions: Array<out String>,//权限数组
        grantResults: IntArray//授权结果
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty()) {
                    for (result in grantResults) {
                        Toast.makeText(this, "请确保权限的正常授予", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
            }
        }
    }

    //授予权限
    private fun getPermission() {
        val permissionList = ArrayList<String>()
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_NETWORK_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_WIFI_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_WIFI_STATE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CHANGE_WIFI_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.CHANGE_WIFI_STATE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.INTERNET)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS)
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 1)
        }
    }

    //初始化位置
    private fun locatInit() {
        AMapLocationClient.updatePrivacyShow(this, true, true)
        AMapLocationClient.updatePrivacyAgree(this, true)
        //声明AMapLocationClient类对象并初始化
        var mLocationClient = AMapLocationClient(applicationContext)
        //声明定位回调监听器
        val mLocationListener = AMapLocationListener() {}
        //AMapLocationClientOption对象用来设置发起定位的模式和相关参数。
        var mLocationOption = AMapLocationClientOption();
        //设置场景为签到
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn)
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation()
            mLocationClient.startLocation()
        }
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true)
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);
        //设置定位请求超时时间,单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation()
        //设置定位回调监听
        mLocationClient.setLocationListener(MyLocationListener())
    }

    inner class MyLocationListener : AMapLocationListener {
        override fun onLocationChanged(p0: AMapLocation) {
            when (p0.getErrorCode()) {
                0 -> {
                    Log.e(
                        "AmapError", "location Error, ErrCode:"
                                + p0.getErrorCode() + ", errInfo:"
                                + p0.getErrorInfo()
                    );
                }
            }

            val locationStr = p0.poiName
            Log.d("location", locationStr)
            val locate = binding.location
            locate.text = locationStr
        }
    }

    //下方商店适配器
    private fun adapterOnClick(shop: Shop) {
        val intent = Intent(this, ShopDetail_activity()::class.java)
        intent.putExtra(ShopID, shop.id)
        startActivity(intent)
    }

    private fun check_status(): Boolean {
        val prefs = getSharedPreferences("Account", MODE_PRIVATE)
        val status = prefs.getString("state", "login in")
        return status == "login in"

    }
}