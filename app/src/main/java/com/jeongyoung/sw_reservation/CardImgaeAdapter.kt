package com.jeongyoung.sw_reservation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeongyoung.sw_reservation.databinding.CardListBinding

class CardImgaeAdapter : RecyclerView.Adapter<CardImgaeAdapter.Holder>() {
    val cardList = arrayListOf<Int>(
        R.drawable.cardpick1,
        R.drawable.cardpick2,
        R.drawable.cardpick3,
        R.drawable.cardpick4,
        R.drawable.cardpick5
    )

    inner class Holder(val binding: CardListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = CardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.cardImage.setImageResource(cardList[position])
    }

    override fun getItemCount() = cardList.size
}