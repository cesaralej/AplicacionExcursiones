package com.example.tickettoto.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tickettoto.models.User

import com.example.tickettoto.R
import com.firebase.ui.common.ChangeEventType
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot


class UsersAdapter(mOptions: FirebaseRecyclerOptions<User>)
    : FirebaseRecyclerAdapter<User, UsersAdapter.MyViewHolder>(mOptions){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name: TextView = itemView.findViewById(R.id.userRecyclerCardName)
        private var email: TextView = itemView.findViewById(R.id.userRecyclerCardEmail)
        private var status: TextView = itemView.findViewById(R.id.userRecyclerCardStatus)

        fun bind(user: User) {
            name.text = user.name
            email.text = user.email
            status.text = "Adentro"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card_recycler_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int, model: User) {
        viewHolder.bind(model)
    }

    override fun onChildChanged(type: ChangeEventType, snapshot: DataSnapshot, newIndex: Int, oldIndex: Int) {
        super.onChildChanged(type, snapshot, newIndex, oldIndex)
//        rcvListMessage.scrollToPosition(newIndex)
    }
}