package com.neo.androidmvimain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neo.androidmvimain.ui.login.LoginFragment
import com.neo.androidmvimain.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .commitNow()
        }
    }

    fun navigateToProfile(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ProfileFragment())
            .commitNow()
    }
}