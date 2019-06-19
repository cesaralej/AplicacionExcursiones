package com.example.tickettoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import com.example.tickettoto.fragments.HomeFragment
import com.example.tickettoto.fragments.LoginFragment
import com.example.tickettoto.helpers.FragmentHandler
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentHandler: FragmentHandler
    private lateinit var actionBar : ActionBar
    private lateinit var fragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        actionBar = supportActionBar!!
//        actionBar.setDisplayHomeAsUpEnabled(true)

        fragmentManager = supportFragmentManager
        fragmentHandler = FragmentHandler(this, R.id.main_fragment_container)

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            fragmentHandler.add(LoginFragment.getInstance())

        } else {
            fragmentHandler.add(HomeFragment.getInstance())
        }

    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        this.finish()
//    }
    //    private val navigationBackPressListener = View.OnClickListener { fragmentManager.popBackStack() }
}
