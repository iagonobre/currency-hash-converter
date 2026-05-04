package com.iago.currencyhashconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iago.currencyhashconverter.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {

    private val _rates = MutableLiveData<Map<String, Double>>()
    val rates: LiveData<Map<String, Double>> = _rates

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchRates(base: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRates(base)
                _rates.value = response.rates
            } catch (e: Exception) {
                _error.value = "Erro: ${e.message}"
            }
        }
    }
}