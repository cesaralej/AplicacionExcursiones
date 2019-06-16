package com.example.tickettoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tickettoto.fragments.LoginFragment
import com.example.tickettoto.helpers.FragmentHandler
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentHandler: FragmentHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        fragmentHandler = FragmentHandler(this, R.id.main_fragment_container)

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            fragmentHandler.add(LoginFragment.getInstance())

        } else {
//            fragmentHandler.add(UserFragment())
        }

    }
}
