package com.msker.bat.ui.screens.calc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.msker.bat.R
import com.msker.bat.databinding.FragmentCalkBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Calk : Fragment() {
    private val f by lazy { FragmentCalkBinding.inflate(layoutInflater) }
    private val viewModel:CalkViewModel by viewModels()
    private var depoSum = 0.0
    private var depoPercent = 0.0
    private var addingSum = 0.0
    private var term = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpFields()
        listeners()
        observer()
        return f.root
    }

    private fun observer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.sum.collect{
                    f.tvTotalSum.text = String.format("%.2f", it)
                }
            }
        }
    }

    private fun listeners() {
        listenSwitch()
        listenFields()
        listenSlider()
        listenButton()
    }

    private fun listenButton() {
        f.btnBtn.setOnClickListener {
            f.root.clearFocus()
            viewModel.calculate(depoSum, depoPercent, addingSum, term)
        }
    }

    private fun listenSlider() {
        f.slider.addOnChangeListener { _, value, _ ->
            term = value.toDouble()
        }
    }

    private fun listenFields() {
        f.etDepoSum.setOnFocusChangeListener{_, focus->
            if (focus) {
                f.etDepoSum.setText(if(depoSum == 0.0) "" else depoSum.toString())
            } else try {
                depoSum= f.etDepoSum.text.toString().toDouble()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Не верно", Toast.LENGTH_SHORT).show()
            }

        }

        f.etPercent.setOnFocusChangeListener{_, focus->
            if (focus) {
                f.etPercent.setText(if(depoPercent == 0.0) "" else depoPercent.toString())
            } else try {
                depoPercent= f.etPercent.text.toString().toDouble()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Не верно", Toast.LENGTH_SHORT).show()
            }

        }

        f.etAddSum.setOnFocusChangeListener{_, focus->
            if (focus) {
                f.etAddSum.setText(if(addingSum == 0.0) "" else addingSum.toString())
            } else try {
                addingSum=  f.etAddSum.text.toString().toDouble()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Не верно", Toast.LENGTH_SHORT).show()
            }

        }

    }


    private fun listenSwitch() {
        f.swit.setOnCheckedChangeListener { _, isChecked ->
            f.tilAddSum.isEnabled = isChecked
            f.tvAddSum.setTextColor(requireContext().getColor(if(isChecked) R.color.black else R.color.light_grayish_blue ))

           if(!isChecked){
                addingSum =   0.0
               f.etAddSum.setText("")
            }
        }
    }

    private fun setUpFields() {
        val colorList =
            ContextCompat.getColorStateList(requireContext(), R.color.box_stroke_color_selector)
        colorList?.let {
            f.tilDepoSum.setBoxStrokeColorStateList(it)
            f.tilPercent.setBoxStrokeColorStateList(it)
            f.tilAddSum.setBoxStrokeColorStateList(it)
        }
    }


}