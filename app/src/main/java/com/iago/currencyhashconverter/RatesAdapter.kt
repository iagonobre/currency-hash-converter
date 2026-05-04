package com.iago.currencyhashconverter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iago.currencyhashconverter.databinding.ItemRateBinding

class RatesAdapter(private val rates: Map<String, Double>) :
    RecyclerView.Adapter<RatesAdapter.RateViewHolder>() {

    inner class RateViewHolder(val binding: ItemRateBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding = ItemRateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val entry = rates.entries.toList()[position]
        holder.binding.tvCurrency.text = entry.key
        holder.binding.tvRate.text = String.format("%.2f", entry.value)
    }

    override fun getItemCount() = rates.size
}