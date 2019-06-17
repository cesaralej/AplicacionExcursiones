package com.example.tickettoto.helpers

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar

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
}