package com.example.homework.login


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework.R
import com.example.homework.m.ToolUtils
import com.example.homework.databinding.ActivityLoginBinding
import com.example.homework.m.RegistResultContract
import java.util.regex.Pattern

/**
 * 登录
 */

class LoginActivity : AppCompatActivity() {
    var shpsd = true

    private lateinit var binding:ActivityLoginBinding

    private val ActivityResultFromRegist = registerForActivityResult(RegistResultContract()){ result ->
        //注册成功销毁
        if (result == 1){
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val LoginId = binding.loginAccount
        val LoginPasssword = binding.loginPassword
        val SHpassword = binding.hspass
        val RememberPassword = binding.rememberPassword
        val SignUp = binding.SignUp
        val LoginIn = binding.loginIn

        //读取上一次配置
        val prefs = getSharedPreferences("Account", MODE_PRIVATE)
        val account = prefs.getString("account","Unknown")
        val password = prefs.getString("password","Unknown")
        val checkBox = prefs.getBoolean("checkBox",false)
        if(checkBox){
            RememberPassword.isChecked=true
            LoginId.setText(account)
            LoginPasssword.setText(password)
        }
        //防止输入数字以外的字符
        LoginId.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val edit = LoginId.text.toString()
                val regEx = "[^0-9]"
                val r = Pattern.compile(regEx)
                val m = r.matcher(edit)
                val str = m.replaceAll("").trim()//删掉不是字母或数字的字符
                if(!edit.equals(str)){
                    LoginId.setText(str)
                    LoginId.setSelection(str.length)
                }

            }
            override fun afterTextChanged(s: Editable?) {
            }

        })
        //跳转注册
        SignUp.setOnClickListener {
            ActivityResultFromRegist.launch(1)
        }
        //显示密码
        SHpassword.setOnClickListener {
            shpsd = if (shpsd){
                SHpassword.setImageResource(R.drawable.disshowpassword)
                LoginPasssword.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                false
            } else{
                SHpassword.setImageResource(R.drawable.showpassword)
                LoginPasssword.setTransformationMethod(PasswordTransformationMethod.getInstance())
                true
            }
        }
        //登入
        LoginIn.setOnClickListener {
//            System.out.println(ToolUtils.getWindowHeigh(this))
            val edit = getSharedPreferences("Account", MODE_PRIVATE).edit()
            //用来与服务器通讯，检验
            edit.putString("state", "check out")
            //保存密码
            if (RememberPassword.isChecked){
                edit.putString("account", LoginId.text.toString())
                edit.putString("password", LoginPasssword.text.toString())
                edit.putBoolean("checkBox",true)
                edit.apply()
            }
            else{
                edit.putBoolean("checkBox",false)
            }
            finish()
        }
    }

}