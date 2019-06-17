package com.example.tickettoto.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

import com.example.tickettoto.R
import com.example.tickettoto.adapters.UsersAdapter
import com.example.tickettoto.models.User
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
        val usersChildPath = "users"
        val TAG = "HOME_FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(activity!!)

        val database = FirebaseDatabase.getInstance()
        database.setPersistenceEnabled(true)

        val usersRef = database.reference.child(usersChildPath)
        usersRef.keepSynced(true)

        val mQuery = usersRef.orderByKey()

        val mOptions = FirebaseRecyclerOptions.Builder<User>()
                .setQuery(usersRef, User::class.java)
                .setLifecycleOwner(this)
                .build()

        val usersAdapter = UsersAdapter(mOptions)

        Log.d(TAG, usersAdapter.snapshots.toString())

        homeUsersRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = usersAdapter
        }
    }
}
