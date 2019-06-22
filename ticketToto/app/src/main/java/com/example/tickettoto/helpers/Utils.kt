package com.example.tickettoto.helpers

import android.app.Activity
import android.app.Dialog
import android.content.res.ColorStateList
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Priority
import com.example.tickettoto.MainActivity
import com.example.tickettoto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

object Utils {
    fun getCircularProgressDrawable(activity: Activity): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(activity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    fun showLoading(activity: Activity): Dialog {
        val progressDialog = Dialog(activity)
        val dialog = ProgressBar(activity)
        dialog.isIndeterminate = true
        dialog.visibility = View.VISIBLE
        progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.setContentView(dialog)
        progressDialog.show()
        return progressDialog
    }

    fun showSnackbar(rootLayout : View, text : String ) {
        Snackbar.make(
                rootLayout,
                text,
                Snackbar.LENGTH_SHORT
        ).show()
    }

    fun glideRequestOptions(activity: Activity): RequestOptions {
        return RequestOptions()
                .centerCrop()
                .placeholder(getCircularProgressDrawable(activity))
                .apply(RequestOptions.circleCropTransform())
                .priority(Priority.HIGH)
    }

    fun tagReader(activity: Activity, view: View, button: FloatingActionButton, userId: String = "") {
        val mainActivity = activity as MainActivity
        if (mainActivity.reading) {
            mainActivity.stopReading()
            showSnackbar(view, activity.getString(R.string.fragment_home_menu_snackbar_reader_disabled))
        } else {
            mainActivity.startReading(userId)
            showSnackbar(view, activity.getString(R.string.fragment_home_menu_snackbar_reader_enabled))
        }
        setNFCFabColor(activity, button, mainActivity.reading)
    }

    private fun setNFCFabColor(activity: Activity, button: FloatingActionButton, reading: Boolean) {
        if (reading) {
            button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.reading))
        } else {
            button.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.colorAccent))
        }
    }

    fun setUserTagColor(activity: Activity, tag: View, tagValue: String?) {
        if (tagValue.isNullOrBlank()) {
            tag.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.unChecked))
        } else {
            tag.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity, R.color.checked))
        }
    }
}