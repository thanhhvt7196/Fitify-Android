package com.example.fitifyandroid.presentation.screens.onboarding.fitnessTool

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fitifyandroid.domain.models.FitnessTool
import com.example.fitifyandroid.presentation.screens.onboarding.views.OnboardingTextItemView

class FitnessToolAdapter(fitnessTools: List<Pair<FitnessTool, Boolean>>) :
    RecyclerView.Adapter<FitnessToolAdapter.ViewHolder>() {

    private var dataSet: List<Pair<FitnessTool, Boolean>> = fitnessTools

    interface FitnessToolAdapterDelegate {
        fun didSelectTool(tool: FitnessTool)
    }

    private var delegate: FitnessToolAdapterDelegate? = null

    fun updateData(newData: List<Pair<FitnessTool, Boolean>>) {
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

    fun setAction(delegate: FitnessToolAdapterDelegate) {
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
        fun bind(item: Pair<FitnessTool, Boolean>) {
            val itemView = itemView as OnboardingTextItemView?
            itemView?.config(item.first.getTitle(itemView.context), null)
            itemView?.isSelected = item.second
            itemView?.setOnClickListener {
                delegate?.didSelectTool(item.first)
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        delegate = null
    }
}