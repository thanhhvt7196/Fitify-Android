package com.example.learnandroid.presentation.screens.onboarding.badHabit

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.domain.models.BadHabit
import com.example.learnandroid.presentation.screens.onboarding.views.OnboardingTextItemView

class BadHabitAdapter(badHabits: List<Pair<BadHabit, Boolean>>) :
    RecyclerView.Adapter<BadHabitAdapter.ViewHolder>() {

    private var dataSet: List<Pair<BadHabit, Boolean>> = badHabits

    interface BadHabitAdapterDelegate {
        fun didSelectBadHabit(badHabit: BadHabit)
    }

    private var delegate: BadHabitAdapterDelegate? = null

    fun updateData(newData: List<Pair<BadHabit, Boolean>>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = dataSet.size
            override fun getNewListSize(): Int = newData.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition].first == newData[newItemPosition].first
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition] == newData[newItemPosition]
            }
        })
        dataSet = newData
        diffResult.dispatchUpdatesTo(this)
    }

    fun setAction(delegate: BadHabitAdapterDelegate) {
        this.delegate = delegate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = OnboardingTextItemView(parent.context, null)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < dataSet.count()) {
            val item = dataSet[position]
            holder.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pair<BadHabit, Boolean>) {
            val itemView = itemView as OnboardingTextItemView?
            itemView?.config(item.first.getTitle(itemView.context), null)
            itemView?.isSelected = item.second
            itemView?.setOnClickListener {
                delegate?.didSelectBadHabit(item.first)
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        delegate = null
    }
}