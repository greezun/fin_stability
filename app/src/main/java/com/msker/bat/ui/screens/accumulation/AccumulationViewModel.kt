package com.msker.bat.ui.screens.accumulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msker.bat.data.FinBase
import com.msker.bat.data.SavingsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccumulationViewModel @Inject constructor(
    private val dataBase: FinBase
) : ViewModel() {
    private val _listItem = MutableStateFlow<List<SavingsData>>(emptyList())
    val listItem = _listItem.asStateFlow()

    private val _balance = MutableStateFlow(0.0)
    val balance = _balance.asStateFlow()

    init {
        update()
    }

    private fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            val itemList = dataBase.getSavingDao().getAll()
            val sum = getSum(itemList)
            _listItem.emit(itemList)
            _balance.emit(sum)
        }
    }

    private fun getSum(itemList: List<SavingsData>): Double {
        return itemList.sumOf { it.sum }
    }

    fun add(item: SavingsData) {
        viewModelScope.launch(Dispatchers.IO) {
            dataBase.getSavingDao().insert(item)
            update()
        }
    }

    fun clear(){
        viewModelScope.launch(Dispatchers.IO) {
            dataBase.getSavingDao().deleteAll()
            update()
        }
    }
}