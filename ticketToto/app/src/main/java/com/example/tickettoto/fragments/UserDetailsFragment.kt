package com.example.tickettoto.fragments


import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tickettoto.MainActivity

import com.example.tickettoto.R
import kotlinx.android.synthetic.main.fragment_user_details.*
import com.example.tickettoto.helpers.Utils
import com.example.tickettoto.lib.Firestore
import com.example.tickettoto.models.User
import com.google.firebase.firestore.CollectionReference

class UserDetailsFragment : Fragment() {

    private lateinit var user: User
    private lateinit var usersCollection: CollectionReference

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

    override fun onDestroy() {
        super.onDestroy()
        (activity!! as MainActivity).stopReading()
    }

    override fun onResume() {
        super.onResume()
        userDetailsPhoneTextView.isEnabled = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersCollection = Firestore.usersCollection()

        Glide.with(activity!!).load(user.profilePicture.url)
                .apply(RequestOptions().placeholder(Utils.getCircularProgressDrawable(activity!!)))
                .apply(Utils.glideRequestOptions(activity!!))
                .into(userDetailsProfileImageView)

        userDetailsFullNameTextView.text = activity!!.getString(R.string.user_card_recycler_view_name, user.firstName, user.lastName)
        userDetailsEmailTextView.text = user.email
        userDetailsPhoneTextView.text = user.phone
        userDetailsBirthdayTextView.text = user.birthday
        userDetailsCompanyTextView.text = user.company
        userDetailsPassportTextView.text = user.passport
        userDetailsDestinationTextView.text = user.destination
        userDetailsFamiliarFullNameTextView.text = user.familiarFullName
        userDetailsFamiliarPhoneTextView.text = user.familiarPhone

        userDetailsPhoneTextView.setOnClickListener {
            userDetailsPhoneTextView.isEnabled = false
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${user.phone}")
            }
            if (intent.resolveActivity(activity!!.packageManager) != null) {
                startActivity(intent)
            }
        }

        Utils.setUserTagColor(activity!!, userDetailsTag, user.tag)
        setStatus()

        userDetailsCheckButton.setOnClickListener {
            user.status = !user.status
            usersCollection.document(user.id).update("status", user.status)
                    .addOnSuccessListener {
                        Utils.showSnackbar(view, activity!!.getString(R.string.fragment_home_menu_snackbar_status_updated,
                            user.firstName,
                            user.lastName))
                        setStatus()
                    }
        }

        userDetailsReaderButton.setOnClickListener {
            Utils.tagReader(activity!!, view, userDetailsReaderButton, user.id)
        }

    }

    private fun setStatus() {
        if (user.status) {
            userDetailsStatus.background = activity!!.getDrawable(R.drawable.ic_check_circle_green_48dp)
            userDetailsCheckButton.setImageDrawable(activity!!.getDrawable(R.drawable.ic_close_white_24dp))
            userDetailsCheckButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, R.color.unChecked))
        } else {
            userDetailsStatus.background = activity!!.getDrawable(R.drawable.ic_remove_circle_grey_48dp)
            userDetailsCheckButton.setImageDrawable(activity!!.getDrawable(R.drawable.ic_check_white_24dp))
            userDetailsCheckButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, R.color.checked))
        }
    }

}
