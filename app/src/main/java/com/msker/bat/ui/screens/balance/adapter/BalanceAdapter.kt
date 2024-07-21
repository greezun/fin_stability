package com.msker.bat.ui.screens.balance.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msker.bat.R
import com.msker.bat.data.BalanceItemData
import com.msker.bat.databinding.AcumItemBinding
import com.msker.bat.ui.screens.balance.adapter.diffUtill.DiffUtilCallback

import java.text.SimpleDateFormat

class BalanceAdapter : ListAdapter<BalanceItemData, BalanceAdapter.BalanceHolder>(DiffUtilCallback()) {


    inner class BalanceHolder(private val binding: AcumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(item: BalanceItemData) {
            binding.date.text = SimpleDateFormat("dd/MM/yyyy").format(item.time)
            val color = if (item.isIncome) R.color.dark_green else R.color.pink
            binding.deskr.text = item.descr
            binding.deskr.setTextColor(ContextCompat.getColor(binding.root.context, color))
            binding.sum.text = item.sum.toString()
            binding.sum.setTextColor(ContextCompat.getColor(binding.root.context, color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AcumItemBinding.inflate(inflater, parent, false)
        return BalanceHolder(binding)
    }

    override fun onBindViewHolder(holder: BalanceHolder, position: Int) {
        holder.bind(getItem(position))
    }
}