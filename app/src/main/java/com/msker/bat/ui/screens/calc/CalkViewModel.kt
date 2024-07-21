package com.msker.bat.ui.screens.calc

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@HiltViewModel
class CalkViewModel @Inject constructor() : ViewModel() {

    private val _sum = MutableStateFlow(0.0)
    val sum = _sum.asStateFlow()


    fun calculate(depoSum: Double, depoPercent: Double, addingSum: Double, term: Double) {
        val percentPerMonth = depoPercent / 100 / 12
        var totalSum = depoSum
        for (i in 1..term.toInt()) {
            totalSum += totalSum * percentPerMonth
            totalSum += addingSum
        }
        _sum.value = totalSum
    }

}