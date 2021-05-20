package com.jeongyoung.sw_reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeongyoung.sw_reservation.databinding.FoodListItemBinding
import com.jeongyoung.sw_reservation.databinding.LocalListBinding

class middleLayoutAdapter : RecyclerView.Adapter<middleLayoutAdapter.middleHolder>() {
    val listData = arrayListOf<String>(
        "경기대",
        "연무동",
        "신사역",
        "가로수길",
        "강남",
        "압구정",
        "홍대",
        "연남동",
        "산본",
        "인계동",
        "부산",
        "제주도"
    )

    inner class middleHolder(val binding: LocalListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setMemo(memo: String) {
            binding.local.text = memo
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): middleLayoutAdapter.middleHolder {
        val binding = LocalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return middleHolder((binding))
    }

    override fun onBindViewHolder(holder: middleLayoutAdapter.middleHolder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount() = listData.size

}
