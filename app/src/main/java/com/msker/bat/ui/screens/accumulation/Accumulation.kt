package com.msker.bat.ui.screens.accumulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.msker.bat.R
import com.msker.bat.data.SavingsData
import com.msker.bat.databinding.FragmentAccumulationBinding
import com.msker.bat.ui.screens.accumulation.adapter.SavingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Accumulation : Fragment() {


    private val viewModel: AccumulationViewModel by viewModels()
    private val f by lazy {
        FragmentAccumulationBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: SavingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = SavingAdapter()
        f.savRecycler.layoutManager = LinearLayoutManager(requireContext())
        f.savRecycler.adapter = adapter
        setListener()
        setObservers()
        return f.root
    }

    private fun setObservers() {
        lifecycleScope.apply {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.listItem.collect {
                        adapter.submitList(it)
                    }
                }
            }
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.balance.collect {
                        f.tvAccumSum.text = it.toString()
                    }
                }
            }
        }
    }


    private fun setListener() {
        f.btnAddS.setOnClickListener {
            addDialog {
                viewModel.add(it)
            }
        }

        f.btnClear.setOnClickListener {
            viewModel.clear()
        }
    }

    private fun addDialog(callBack: (SavingsData) -> Unit) {
        val dialog = AlertDialog.Builder(requireContext())
        var data = SavingsData()

        val customLayout: View = layoutInflater.inflate(R.layout.dialog, null)
        val sumField = customLayout.findViewById<TextInputEditText>(R.id.et_sum)
        val artField = customLayout.findViewById<TextInputEditText>(R.id.et_article)
        artField.visibility = View.GONE

        sumField.setOnFocusChangeListener { _, focus ->
            if (focus) {
                sumField.setText("")
            } else try {
                data = data.copy(sum = sumField.text.toString().toDouble())
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Не верно", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.setView(customLayout)

        dialog.setPositiveButton("Сохранить") { _, _ ->
            sumField.clearFocus()
            artField.clearFocus()
            callBack(data)

        }

        dialog.setNegativeButton("Отмена") { _, _ ->

        }

        dialog.show()
    }


}