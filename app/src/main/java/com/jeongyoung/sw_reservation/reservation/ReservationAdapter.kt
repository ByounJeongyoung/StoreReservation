package com.jeongyoung.sw_reservation.reservation

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeongyoung.sw_reservation.MainFragment.FragmentActivity
import com.jeongyoung.sw_reservation.databinding.FragmentReservation2Binding
import java.text.SimpleDateFormat
import java.util.*


class ReservationAdapter(private  val reservationClickedlistener:(ReservationModel) -> Unit): ListAdapter<ReservationModel, ReservationAdapter.ViewHolder>(diffUtill) {

    inner class ViewHolder(private val binding: FragmentReservation2Binding): RecyclerView.ViewHolder(binding.root){

        fun bind(articleModel : ReservationModel){


            val format = SimpleDateFormat("MM월 dd일")
            val date = Date(articleModel.crateAT)

            binding.name.text = articleModel.name
            binding.people.text = articleModel.people
            binding.time.text = format.format(date).toString()
            binding.root.setOnClickListener {
                reservationClickedlistener(articleModel)
            }

           }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentReservation2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    companion object{
        val diffUtill = object : DiffUtil.ItemCallback<ReservationModel>(){
            override fun areItemsTheSame(oldItem: ReservationModel, newItem: ReservationModel): Boolean {
                return oldItem.crateAT == newItem.crateAT
            }

            override fun areContentsTheSame(oldItem: ReservationModel, newItem: ReservationModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}