package com.example.tickettoto.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import com.example.tickettoto.R
import com.example.tickettoto.helpers.FragmentHandler
import com.example.tickettoto.helpers.Utils
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    companion object {
        fun getInstance(): LoginFragment = LoginFragment()
        val TAG = "LOGIN_FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHandler = FragmentHandler(activity!! as AppCompatActivity, R.id.main_fragment_container)

        mAuth = FirebaseAuth.getInstance()

        setEditTextValidation(userNameEditText, getString(R.string.fragment_login_username_error))
        setEditTextValidation(passwordEditText, getString(R.string.fragment_login_password_error))

        loginButton.setOnClickListener {
            val progressDialog = Utils.showLoading(activity!!)
            FirebaseAuth.getInstance().signInWithEmailAndPassword(userNameEditText.editText!!.text.toString(), passwordEditText.editText!!.text.toString())
                .addOnCompleteListener(activity!!) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, mAuth.currentUser!!.email.toString())
                        progressDialog.hide()
                        fragmentHandler.add(HomeFragment.getInstance())
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        progressDialog.hide()
                        Utils.showSnackbar(getView()!!, activity!!.getString(R.string.fragment_login_snackbar_error_auth_failed))
                    }
                }
        }

    }


    private fun setEditTextValidation(editTextLayout: TextInputLayout, errorValue: String){
        editTextLayout.editText!!.afterTextChanged { validateEditText(editTextLayout, editTextLayout.editText!!, errorValue) }
        editTextLayout.editText!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEditText(editTextLayout, editTextLayout.editText!!, errorValue)
            }
        }
    }

    private fun validateEditText(layout: TextInputLayout, editText: EditText, errorValue: String) {
        if (editText.text.isEmpty()) layout.error = errorValue else layout.error = null
    }


    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}
