package com.jeongyoung.sw_reservation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.jeongyoung.sw_reservation.databinding.ActivityReservationBinding

//나의 예약 페이지
class StoreReservationActivity : AppCompatActivity() {

    val binding by lazy { ActivityReservationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //현재 sharedPreference 작성
        val shared = getSharedPreferences("storeName",Context.MODE_PRIVATE)

        binding.nameText.text = shared.getString("name","")
        binding.numText.text =shared.getString("peopleNum","")
        binding.timeText.text  =shared.getString("reserveTime","")

        //binding.nameText.text = "변정영"
        //binding.numText.text = "3명"
        //가게 상세위치 제공
        //StoreReservationMapActivity -> 상세정보 전용 페이지
        binding.map.setOnClickListener {
            val intent = Intent(this, StoreReservationMapActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.review.setOnClickListener {
            startActivity(Intent(this@StoreReservationActivity,Review::class.java))

        }

    }
}