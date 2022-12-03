package com.example.homework.sign

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.homework.databinding.ActivitySignBinding
import java.util.regex.Pattern

/**
 * 注册
 */
class SignActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val SignAccount = binding.signupAccount
        val SignPassword = binding.signupPassword
        val ConfirmPassword = binding.confirmPassword
        val regist = binding.regist
        //注册并登入
        //判断密码账号是否为空或者含有空格，保证两次密码相同
        //防止输入数字以外的字符
        SignAccount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val edit = SignAccount.text.toString()
                val regEx = "[^0-9]"
                val r = Pattern.compile(regEx)
                val m = r.matcher(edit)
                val str = m.replaceAll("").trim()//删掉不是字母或数字的字符
                if(!edit.equals(str)){
                    SignAccount.setText(str)
                    SignAccount.setSelection(str.length)
                }

            }
            override fun afterTextChanged(s: Editable?) {
            }

        })

        regist.setOnClickListener {
            //Log.d("ceshiyixiababnab", SignAccount.text.toString())
            if(SignAccount.text.toString()!=""&&SignPassword.text.toString()!=""&&ConfirmPassword.text
                    .toString()!=""){
                if(SignPassword.text.toString()==ConfirmPassword.text.toString()){
                    //与服务器通讯暂未完成

                    //确认登入跳转
                    val edit = getSharedPreferences("Account", MODE_PRIVATE).edit()
                    edit.putString("state", "check out")
                    edit.apply()
                    val intent = Intent().apply{
                        putExtra("result",1)
                    }
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"请确保两次输入密码一致",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"请确保输入为非空",Toast.LENGTH_SHORT).show()
            }
        }
    }
}