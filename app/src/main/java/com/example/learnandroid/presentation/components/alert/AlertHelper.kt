package com.example.learnandroid.presentation.components.alert

import android.content.Context
import android.content.DialogInterface.OnShowListener
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.learnandroid.FitifyApplication
import com.example.learnandroid.R


object AlertHelper {
    fun showAlert(
        context: Context,
        title: String?,
        message: String?,
        confirmTitle: String? = FitifyApplication.mContext.getString(
            R.string.ok
        )
    ) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(confirmTitle) { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(context, R.color.blue_dark))
        }
        alertDialog.show()
    }
}