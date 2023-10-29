package com.example.learnandroid.presentation.screens.onboarding.commitContract

import android.content.Context
import com.example.learnandroid.R
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingCommitContractViewModel: BaseViewModel() {
    enum class CommitState: Iterable<CommitState> {
        NONE {
            override fun getTitle(context: Context): String? = null
        },
        HOLD_IT {
            override fun getTitle(context: Context): String? = context.getString(R.string.onboarding_commit_contract_hold_it)
        },
        KEEP_GOING {
            override fun getTitle(context: Context): String? = context.getString(R.string.onboarding_commit_contract_keep_going)
        },
        AWESOME {
            override fun getTitle(context: Context): String? = context.getString(R.string.onboarding_commit_contract_awesome)
        };

        abstract fun getTitle(context: Context): String?

        override fun iterator(): Iterator<CommitState> = enumValues<CommitState>().iterator()
    }

    private val _state = MutableStateFlow(CommitState.NONE)
    val state = _state.asStateFlow()

    fun setState(state: CommitState) {
        _state.value = state
    }
}