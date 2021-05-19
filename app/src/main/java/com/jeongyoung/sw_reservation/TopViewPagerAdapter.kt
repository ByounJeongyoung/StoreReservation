package com.jeongyoung.sw_reservation

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.jeongyoung.sw_reservation.MainFragment.FragmentActivity
import com.jeongyoung.sw_reservation.databinding.FoodListItemBinding

class TopViewPagerAdapter(val foodListfunc : ArrayList<Int>,) : RecyclerView.Adapter<TopViewPagerAdapter.Holder>() {



    inner class Holder(val binding: FoodListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, FragmentActivity::class.java)
                startActivity(binding.root.context,intent,null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            FoodListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.imageViewFood.setImageResource(foodListfunc[position])
    }

    override fun getItemCount() = foodListfunc.size
}