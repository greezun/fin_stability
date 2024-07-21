package com.msker.bat.ui.screens.balance.adapter.diffUtill

import androidx.recyclerview.widget.DiffUtil
import com.msker.bat.data.BalanceItemData


class DiffUtilCallback : DiffUtil.ItemCallback<BalanceItemData>() {

    override fun areItemsTheSame(oldItem: BalanceItemData, newItem: BalanceItemData): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: BalanceItemData, newItem: BalanceItemData): Boolean {
        return oldItem == newItem
    }
}