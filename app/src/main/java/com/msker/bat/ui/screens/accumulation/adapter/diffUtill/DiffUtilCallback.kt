package com.msker.bat.ui.screens.accumulation.adapter.diffUtill

import androidx.recyclerview.widget.DiffUtil
import com.msker.bat.data.SavingsData


class DiffUtilCallback : DiffUtil.ItemCallback<SavingsData>() {

    override fun areItemsTheSame(oldItem: SavingsData, newItem: SavingsData): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: SavingsData, newItem: SavingsData): Boolean {
        return oldItem == newItem
    }
}