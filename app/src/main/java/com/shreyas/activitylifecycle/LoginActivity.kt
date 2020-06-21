package com.shreyas.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {


    lateinit var etMobile: EditText
    lateinit var etPass: EditText
    lateinit var btnLogin: Button
    lateinit var txtForgotPass: TextView
    lateinit var txtRegister: TextView
    val validMobile = "1234567890"
    val validPass = arrayOf("tony", "steve", "bruce", "thanos")
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(getString(R.string.references_file_name), Context.MODE_PRIVATE)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        setContentView(R.layout.activity_login)

        if(isLoggedIn) {
            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)
            startActivity(intent)
            finish()
        }
        title = "Avengers Assemble"

        etMobile = findViewById(R.id.etMobile)
        etPass = findViewById(R.id.etPass)
        btnLogin = findViewById(R.id.btnLogin)
        txtForgotPass = findViewById(R.id.txtForgotPass)
        txtRegister = findViewById(R.id.txtRegister)

        btnLogin.setOnClickListener {
            val mobile = etMobile.text.toString()

            val pass = etPass.text.toString()

            var nameOfAvenger = "Avenger"

            val intent = Intent(this@LoginActivity, AvengersActivity::class.java)

            if ((mobile == validMobile)) {
                when (pass) {
                    validPass[0] -> {

                        nameOfAvenger = "Iron Man"
                        savePreferences(nameOfAvenger)
                        intent.putExtra("Name", nameOfAvenger)
                        startActivity(intent)
                    }
                    validPass[1] -> {
                        nameOfAvenger = "Captain America"
                        savePreferences(nameOfAvenger)
                        intent.putExtra("Name", nameOfAvenger)
                        startActivity(intent)

                    }
                    validPass[2] -> {
                        nameOfAvenger = "The Hulk"
                        savePreferences(nameOfAvenger)
                        intent.putExtra("Name", nameOfAvenger)
                        startActivity(intent)
                    }
                    validPass[3] -> {
                        nameOfAvenger = "The Avengers"
                        savePreferences(nameOfAvenger)
                        intent.putExtra("Name", nameOfAvenger)
                        startActivity(intent)

                    }
                    else -> Toast.makeText(
                        this@LoginActivity,
                        "Incorrect Password",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onPause(){
        super.onPause()
        finish()
    }

    fun savePreferences(title:String){
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
        sharedPreferences.edit().putString("Title",title).apply()
    }

}
