package com.jeongyoung.sw_reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeongyoung.sw_reservation.databinding.DiscountListBinding
import com.jeongyoung.sw_reservation.databinding.RecommendListBinding


class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.Holder>() {
    val recommendList = arrayListOf<Int>(
        R.drawable.recomend1,
        R.drawable.recomend2,
        R.drawable.recomend3,
        R.drawable.recomend4,
        R.drawable.recomend5
    )

    inner class Holder(val binding: RecommendListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RecommendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.recommendImage.setImageResource(recommendList[position])
    }

    override fun getItemCount() = recommendList.size
}