package com.example.learnandroid.presentation.screens.plan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learnandroid.databinding.FragmentPlansBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlansFragment : BaseViewBindingFragment<FragmentPlansBinding, PlansViewModel>(FragmentPlansBinding::inflate) {
    override val viewModel: PlansViewModel by viewModel()

    companion object {
        fun newInstance(): PlansFragment {
            return PlansFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (savedInstanceState == null) {
//            viewModel.type = arguments?.getInt("key") ?: 0;
//        } else {
//            viewModel.type = savedInstanceState.getInt("key")
//        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("key", viewModel.type)
        super.onSaveInstanceState(outState)
    }

    override fun initView() {
        val activity = requireActivity() as? AppCompatActivity

        activity?.supportActionBar?.apply {
            title = "ahihi"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override suspend fun subscribeData() {

    }
}