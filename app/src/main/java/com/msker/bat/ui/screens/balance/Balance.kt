package com.msker.bat.ui.screens.balance


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
import com.msker.bat.data.BalanceItemData
import com.msker.bat.databinding.FragmentBalanceBinding
import com.msker.bat.ui.screens.balance.adapter.BalanceAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Balance : Fragment() {

    private val viewModel: BalanceViewModel by viewModels()
    private lateinit var adapter: BalanceAdapter
    private val f by lazy { FragmentBalanceBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = BalanceAdapter()
        f.balRecycler.layoutManager = LinearLayoutManager(requireContext())
        f.balRecycler.adapter = adapter
        setObservers()
        setListener()
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
        f.btnAdd.setOnClickListener {
            addDialog(true) {
                viewModel.add(it)
            }
        }

        f.btnWithdraw.setOnClickListener {
            addDialog(false) {
                viewModel.add(it)
            }
        }
    }

    private fun addDialog(isIncome: Boolean, callBack: (BalanceItemData) -> Unit) {
        val dialog = AlertDialog.Builder(requireContext())
        var data = BalanceItemData(isIncome = isIncome)

        val customLayout: View = layoutInflater.inflate(R.layout.dialog, null)
        val sumField = customLayout.findViewById<TextInputEditText>(R.id.et_sum)
        val artField = customLayout.findViewById<TextInputEditText>(R.id.et_article)

        sumField.setOnFocusChangeListener { _, focus ->
            if (focus) {
                sumField.setText("")
            } else try {
                data = data.copy(sum = sumField.text.toString().toDouble())
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Не верно", Toast.LENGTH_SHORT).show()
            }
        }

        artField.setOnFocusChangeListener { _, focus ->
            if (focus) {
                artField.setText("")
            } else
                data = data.copy(descr = artField.text.toString())

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