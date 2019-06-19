package com.example.tickettoto.fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

import com.example.tickettoto.R
import com.example.tickettoto.adapters.UsersAdapter
import com.example.tickettoto.helpers.FragmentHandler
import com.example.tickettoto.helpers.Utils
import com.example.tickettoto.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.gson.Gson
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference


class HomeFragment : Fragment() {

    private lateinit var fragmentHandler: FragmentHandler
    private lateinit var usersCollection: CollectionReference
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var users: ArrayList<User>
    private lateinit var allUsers: ArrayList<User>
    private lateinit var filterOption: Number
    private lateinit var menuFilterAllData: MenuItem
    private lateinit var menuFilterCheckedData: MenuItem
    private lateinit var menuFilterUnCheckedData: MenuItem


    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
        val usersCollectionName = "users"
        val TAG = "HOME_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

        val progressDialog = Utils.showLoading(activity!!)

        val db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
        db.firestoreSettings = settings
        usersCollection = db.collection(usersCollectionName)

//        Default filter menu selected option
        filterOption = 2

        users = ArrayList()
        allUsers = ArrayList()

        usersAdapter =  UsersAdapter(activity!!, users,
            object: UsersAdapter.OnClickListener {
                override fun onClick(user: User) {
                    fragmentHandler.add(UserDetailsFragment.getInstance(user), true)
                }
            }
        )

//        TODO: Add snapshot changes listener
        usersCollection.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data
                    data.put("id", document.id)
                    Log.d(TAG, "${document.id} => ${document.data}")
                    allUsers.add(Gson().fromJson(Gson().toJson(data).toString(), User::class.java))
                }
                updateUsers()
                progressDialog.hide()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                progressDialog.hide()
            }

        usersCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                allUsers.clear()
                for (document in snapshot.documents) {
                    val data = document.data
                    data!!.put("id", document.id)
                    Log.d(TAG, "${document.id} => ${document.data}")
                    allUsers.add(Gson().fromJson(Gson().toJson(data).toString(), User::class.java))
                }
                updateUsers()
                Log.d(TAG, "Current data: ${snapshot.documents.toString()}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }

        homeUsersRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = usersAdapter
        }

//        homeUsersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy < 0 && !homeNFCButton.isShown)
//                    homeNFCButton.show()
//                else if (dy > 0 && homeNFCButton.isShown)
//                    homeNFCButton.hide()
//            }
//        })

        homeNFCButton.setOnClickListener {
            usersCollection.document(users[0].id!!).update("status", true).addOnSuccessListener {
                Utils.showSnackbar(view, "${users[0].name} status updated!")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.menuFilterAllData -> {
                homeNFCButton.show()
                updateFilterOption(menuFilterAllData, 0)
            }
            R.id.menuFilterCheckedData -> {
                homeNFCButton.hide()
                updateFilterOption(menuFilterCheckedData, 1)
            }
            R.id.menuFilterUnCheckedData -> {
                homeNFCButton.show()
                updateFilterOption(menuFilterUnCheckedData, 2)
            }
            R.id.resetData -> {
                for (user in allUsers) {
                    usersCollection.document(user.id!!).update("status", false)
                }
            }
            R.id.signOut -> {
                item.isVisible = false
                FirebaseAuth.getInstance().signOut()
                fragmentHandler.add(LoginFragment.getInstance())
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        fragmentHandler = FragmentHandler(activity!! as AppCompatActivity, R.id.main_fragment_container)
        super.onCreateOptionsMenu(menu, inflater)

        menuFilterAllData = menu.findItem(R.id.menuFilterAllData)
        menuFilterCheckedData = menu.findItem(R.id.menuFilterCheckedData)
        menuFilterUnCheckedData = menu.findItem(R.id.menuFilterUnCheckedData)

        menuFilterUnCheckedData.isChecked = true
    }

    private fun updateFilterOption(selectedItem: MenuItem, selectedOption: Number) {
        when(filterOption) {
            0 -> menuFilterAllData.isChecked = false
            1 -> menuFilterCheckedData.isChecked = false
            2 -> menuFilterUnCheckedData.isChecked = false
        }
        filterOption = selectedOption
        selectedItem.isChecked = true
        updateUsers()
    }

    private fun updateUsers() {
        lateinit var usersArrayList: ArrayList<User>
        when(filterOption) {
            0 -> {
                usersArrayList = allUsers.clone() as ArrayList<User>
            }
            1 -> {
                usersArrayList = allUsers.filter { user -> user.status!! } as ArrayList<User>
            }
            2 -> {
                usersArrayList = allUsers.filter { user -> !user.status!! } as ArrayList<User>
            }
            else -> usersArrayList = ArrayList()
        }
        users.clear()
        users.addAll(usersArrayList)
        Log.d(TAG, users.toString())
        usersAdapter.notifyDataSetChanged()
    }
}
