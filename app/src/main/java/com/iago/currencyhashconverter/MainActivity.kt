package com.iago.currencyhashconverter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.iago.currencyhashconverter.databinding.ActivityMainBinding
import com.iago.currencyhashconverter.viewmodel.CurrencyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerRates.layoutManager = LinearLayoutManager(this)

        binding.btnFetch.setOnClickListener {
            val base = binding.editBase.text.toString().uppercase()
            if (base.isNotEmpty()) viewModel.fetchRates(base)
        }

        viewModel.rates.observe(this) { rates ->
            binding.recyclerRates.adapter = RatesAdapter(rates)
            val hash = generateHash(rates.toString())
            binding.tvHash.text = "Hash: $hash"
        }

        viewModel.error.observe(this) { error ->
            binding.tvHash.text = error
        }
    }

    external fun generateHash(input: String): String

    companion object {
        init {
            System.loadLibrary("currencyhashconverter")
        }
    }
}