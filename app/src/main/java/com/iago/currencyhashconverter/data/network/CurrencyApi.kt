package com.iago.currencyhashconverter.data.network

import com.iago.currencyhashconverter.data.model.CurrencyResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest")
    suspend fun getRates(@Query("from") base: String): CurrencyResponse
}