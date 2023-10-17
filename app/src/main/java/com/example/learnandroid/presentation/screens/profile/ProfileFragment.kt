package com.example.learnandroid.presentation.screens.profile

import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.provider.CalendarContract.Colors
import androidx.core.content.ContextCompat
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentProfileBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.utils.BitmapExtension
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseViewBindingFragment<FragmentProfileBinding, ProfileViewModel>(FragmentProfileBinding::inflate) {

    override val viewModel: ProfileViewModel by viewModel()

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun initView() {
        val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.profile_bg_photo)
        if (drawable is BitmapDrawable) {
            val tintColor = ContextCompat.getColor(requireActivity(), R.color.blue_light_0_5)
            val blendedBitmap = BitmapExtension.blendBitmapWithColor(resources, R.drawable.profile_bg_photo, tintColor)
            blendedBitmap.alpha = 200
            viewBinding.imgHeaderBackground.setImageDrawable(blendedBitmap)
        }
    }

    override suspend fun subscribeData() {

    }
}