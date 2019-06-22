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


class UsersAdapter(private val activity: Activity, private val users: ArrayList<User>,
                   private val clickListener: OnClickListener)
    : RecyclerView.Adapter<UsersAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name: TextView = itemView.findViewById(R.id.userRecyclerCardName)
        private var company: TextView = itemView.findViewById(R.id.userRecyclerCardCompany)
        private var destination: TextView = itemView.findViewById(R.id.userRecyclerCardDestination)
        private var status: View = itemView.findViewById(R.id.userRecyclerCardStatus)
        private var tag: View = itemView.findViewById(R.id.userRecyclerCardTag)

        fun bind(activity: Activity, user: User, clickListener: OnClickListener) {
            Glide.with(activity).load(user.profilePicture.url)
                .apply(RequestOptions().placeholder(Utils.getCircularProgressDrawable(activity)))
                .into(itemView.findViewById(R.id.userRecyclerCardProfileImageView))
            name.text = activity.getString(R.string.user_card_recycler_view_name, user.firstName, user.lastName)
            company.text = user.company
            destination.text = user.destination
            status.background = if (user.status) activity.getDrawable(R.drawable.ic_check_circle_green_48dp)
                else activity.getDrawable(R.drawable.ic_remove_circle_grey_48dp)

            Utils.setUserTagColor(activity, tag, user.tag)

            itemView.setOnClickListener {
                clickListener.onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card_recycler_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(activity, users[position], clickListener)
    }

    override fun getItemCount() = users.size

    interface OnClickListener {
        fun onClick(user: User)
    }
}