package com.jeongyoung.sw_reservation.reservation

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeongyoung.sw_reservation.MainFragment.FragmentActivity
import com.jeongyoung.sw_reservation.R
import com.jeongyoung.sw_reservation.databinding.FragmentReservation2Binding
import java.text.SimpleDateFormat
import java.util.*


class ReservationAdapter(private  val reservationClickedlistener:(ReservationModel) -> Unit): ListAdapter<ReservationModel, ReservationAdapter.ViewHolder>(diffUtill) {

    inner class ViewHolder(private val binding: FragmentReservation2Binding): RecyclerView.ViewHolder(binding.root){
        private var done : Boolean = true
        fun bind(articleModel : ReservationModel){


//            val format = SimpleDateFormat("MM월 dd일")
  //          val date = Date(articleModel.crateAT)

            binding.name.text = articleModel.name
            binding.people.text = articleModel.people
            binding.time.text = articleModel.time
            binding.root.setOnClickListener {

                reservationClickedlistener.invoke(articleModel)
                if(done == true){
                    binding.layout.setBackgroundColor(Color.WHITE)
                    done = !done
                }else{
                binding.layout.setBackgroundColor(Color.parseColor("#C5C1BF"))
                done = !done
                }
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
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ReservationModel, newItem: ReservationModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}