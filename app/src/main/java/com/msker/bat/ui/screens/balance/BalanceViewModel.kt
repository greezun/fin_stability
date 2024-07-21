package com.msker.bat.ui.screens.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msker.bat.data.BalanceItemData
import com.msker.bat.data.FinBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    private val dataBase: FinBase
) : ViewModel() {

    private val _listItem = MutableStateFlow<List<BalanceItemData>>(emptyList())
    val listItem = _listItem.asStateFlow()

    private val _balance = MutableStateFlow(0.0)
    val balance = _balance.asStateFlow()

    init {
        update()
    }

    private fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            val itemList = dataBase.getBalanceDao().getAll()
            val sum = getSum(itemList)
            _listItem.emit(itemList)
            _balance.emit(sum)

        }
    }

    private fun getSum(itemList: List<BalanceItemData>): Double {
        var sum = 0.0
        itemList.forEach {
            if (it.isIncome) {
                sum += it.sum
            } else sum -= it.sum
        }

        return sum
    }

    fun add(item: BalanceItemData) {
        viewModelScope.launch(Dispatchers.IO) {
            dataBase.getBalanceDao().insert(item)
            update()
        }
    }


}