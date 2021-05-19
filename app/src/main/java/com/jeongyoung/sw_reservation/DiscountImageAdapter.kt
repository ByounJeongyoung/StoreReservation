package com.jeongyoung.sw_reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeongyoung.sw_reservation.databinding.DiscountListBinding
import java.util.zip.Inflater

class DiscountImageAdapter:RecyclerView.Adapter<DiscountImageAdapter.Holder>(){
    val discountList = arrayListOf<Int>(R.drawable.discount1,R.drawable.discount2,R.drawable.discount3,R.drawable.discount4,R.drawable.discount5,)

    inner class Holder(val binding: DiscountListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    val binding = DiscountListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
     return  Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
      holder.binding.discountImage.setImageResource(discountList[position])
    }

    override fun getItemCount() = discountList.size
}