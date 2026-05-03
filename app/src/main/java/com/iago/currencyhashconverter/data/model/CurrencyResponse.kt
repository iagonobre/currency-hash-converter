package com.iago.currencyhashconverter.data.model

data class CurrencyResponse(
    val base: String,
    val rates: Map<String, Double>
)