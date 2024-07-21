package com.msker.bat.ui.screens.accumulation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msker.bat.R
import com.msker.bat.data.SavingsData
import com.msker.bat.databinding.AcumItemBinding
import com.msker.bat.ui.screens.accumulation.adapter.diffUtill.DiffUtilCallback
import java.text.SimpleDateFormat

class SavingAdapter : ListAdapter<SavingsData, SavingAdapter.SavingHolder>(DiffUtilCallback()) {

    inner class SavingHolder(private val binding: AcumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(item: SavingsData) {
            binding.date.text = SimpleDateFormat("dd/MM/yyyy").format(item.time)
            val color =  R.color.dark_green
            binding.deskr.text = ""
            binding.deskr.setTextColor(ContextCompat.getColor(binding.root.context, color))
            binding.sum.text = item.sum.toString()
            binding.sum.setTextColor(ContextCompat.getColor(binding.root.context, color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AcumItemBinding.inflate(inflater, parent, false)
        return SavingHolder(binding)
    }

    override fun onBindViewHolder(holder: SavingHolder, position: Int) {
        holder.bind(getItem(position))
    }
}