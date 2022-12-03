package com.example.homework.m

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.homework.sign.SignActivity

/**
 * 回传数据
 */

class RegistResultContract:ActivityResultContract<Int,Int>() {
    override fun createIntent(context: Context, input: Int): Intent {
        return Intent(context, SignActivity::class.java).apply {
            putExtra("name",input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int {
        val data = intent?.getIntExtra("result",0)
        return if (resultCode == Activity.RESULT_OK && data != null) data else 0
    }
}