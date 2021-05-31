package com.jeongyoung.sw_reservation

import com.jeongyoung.sw_reservation.reservation.ReservationAdapter
import com.jeongyoung.sw_reservation.reservation.ReservationModel


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
import com.jeongyoung.sw_reservation.databinding.ActivityReviewDetailBinding
import com.jeongyoung.sw_reservation.databinding.FragmentReservation2Binding
import java.text.SimpleDateFormat
import java.util.*


class ReviewAdapter : ListAdapter<ReviewModel, ReviewAdapter.ViewHolder>(diffUtill) {

    inner class ViewHolder(private val binding: ActivityReviewDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reviewModel: ReviewModel) {
            binding.id.text = reviewModel.id
            binding.comment.text = reviewModel.review
            binding.ratingbar.setIsIndicator(false)
            binding.ratingbar.rating = reviewModel.score.toFloat()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityReviewDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtill = object : DiffUtil.ItemCallback<ReviewModel>() {
            override fun areItemsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
                return oldItem.review == newItem.review
            }

            override fun areContentsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}