package com.example.fitifyandroid.presentation.components.progressHUD

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.fitifyandroid.R

class ProgressHUD {

    companion object {
        private var dialog: Dialog? = null

        fun show(context: Context) {
            dismiss()
            dialog = Dialog(context)
            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.progress_hud)
            dialog?.setCancelable(false)
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.show()
        }

        fun dismiss() {
            dialog?.dismiss()
            dialog = null
        }
    }
}