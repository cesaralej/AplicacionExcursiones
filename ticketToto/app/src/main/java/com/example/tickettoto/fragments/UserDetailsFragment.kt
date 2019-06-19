package com.example.tickettoto.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.tickettoto.R
import kotlinx.android.synthetic.main.fragment_user_details.*
import com.example.tickettoto.helpers.Utils
import com.example.tickettoto.models.User


class UserDetailsFragment : Fragment() {

    lateinit var user: User

    companion object {
        fun getInstance(user: User) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("USER", user)
            }
        }
        val TAG = "USER_DETAILS_FRAGMENT"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.getParcelable<User>("USER")?.let {
            user = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(activity!!).load(user.profilePicture)
                .apply(RequestOptions().placeholder(Utils.getCircularProgressDrawable(activity!!)))
                .into(userDetailsProfileImageView)

        userDetailsNameTextView.text = user.name
    }
}
