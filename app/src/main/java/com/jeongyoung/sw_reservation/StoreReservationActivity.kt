package com.jeongyoung.sw_reservation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.jeongyoung.sw_reservation.databinding.ActivityReservationBinding


class StoreReservationActivity : AppCompatActivity() {

    private lateinit var articleDB: DatabaseReference
    val binding by lazy { ActivityReservationBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
/*
        val shared = getSharedPreferences("reservationInform", Context.MODE_PRIVATE)
        val name = shared.getString("storeName", "")
        val num = shared.getString("peoplenum", "")
*/
        binding.nameText.text = "JeongYoung"
        binding.numText.text = "3"


        binding.map.setOnClickListener {
            val intent = Intent(this, StoreReservationMapActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        /*  binding.button.setOnClickListener {


              val intent = Intent(this,StoreReservationDetailActivity::class.java)
              startActivity(intent)
          }*/
    }
}