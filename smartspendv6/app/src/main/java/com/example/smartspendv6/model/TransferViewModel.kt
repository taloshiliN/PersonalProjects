package com.example.smartspendv6.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.smartspendv6.data.Transfer

class TransferViewModel : ViewModel() {
    private val _transfers = mutableStateListOf<Transfer>()
    val transfers: List<Transfer> get() = _transfers


    fun addTransfer(amount: Double, accountOrPhone:Int) {
        _transfers.add(Transfer(amount, accountOrPhone))
    }
}