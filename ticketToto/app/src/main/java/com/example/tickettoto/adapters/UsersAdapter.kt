package com.example.tickettoto.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tickettoto.models.User

import com.example.tickettoto.R
import com.example.tickettoto.helpers.Utils


class UsersAdapter(private val activity: Activity, private val users: ArrayList<User>)
    : RecyclerView.Adapter<UsersAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name: TextView = itemView.findViewById(R.id.userRecyclerCardName)
        private var email: TextView = itemView.findViewById(R.id.userRecyclerCardEmail)
        private var status: TextView = itemView.findViewById(R.id.userRecyclerCardStatus)

        fun bind(activity: Activity, user: User) {
            Glide.with(activity).load(user.profilePicture)
                .apply(RequestOptions().placeholder(Utils.getCircularProgressDrawable(activity)))
                .into(itemView.findViewById(R.id.userRecyclerCardCoverImageView))
            name.text = user.name
            email.text = user.email
            status.text = if (user.status!!) activity.resources.getString(R.string.fragment_home_user_status_true)
                else activity.resources.getString(R.string.fragment_home_user_status_false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card_recycler_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(activity, users[position])
    }

    override fun getItemCount() = users.size

}