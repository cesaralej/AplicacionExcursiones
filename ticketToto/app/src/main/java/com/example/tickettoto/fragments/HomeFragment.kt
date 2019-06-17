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
import com.example.tickettoto.helpers.Utils
import com.example.tickettoto.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class HomeFragment : Fragment() {

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
        val usersCollection = "users"
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

        val db = FirebaseFirestore.getInstance()

        val usersCollection = db.collection(usersCollection)
        val users = ArrayList<User>()

        val usersAdapter = UsersAdapter(activity!!, users)

        val progressDialog = Utils.showLoading(activity!!)
        usersCollection.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    users.add(Gson().fromJson(Gson().toJson(document.data).toString(), User::class.java))
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                usersAdapter.notifyDataSetChanged()
                progressDialog.hide()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                progressDialog.hide()
            }


        homeUsersRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = usersAdapter
        }

//        homeNFCButton.setOnClickListener {
//
//        }
    }
}
