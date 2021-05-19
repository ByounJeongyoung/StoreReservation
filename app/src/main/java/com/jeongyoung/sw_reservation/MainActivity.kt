package com.jeongyoung.sw_reservation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeongyoung.sw_reservation.LocationActivity.LocationActivity
import com.jeongyoung.sw_reservation.MainFragment.FragmentActivity
import com.jeongyoung.sw_reservation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.tuttle.setImageResource(R.drawable.tuttle)  거북이 시도 실패 -> img필요할때 활용



        val foodData = foodListfunc()
        val topViewPagerAdapter = TopViewPagerAdapter(foodData)
        binding.topLayout.adapter = topViewPagerAdapter


        val middleLayoutAdapter = middleLayoutAdapter()
        binding.listLayout.adapter = middleLayoutAdapter
        binding.listLayout.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.HORIZONTAL, false
        )

        val cardImgaeAdapter = CardImgaeAdapter()
        binding.card.adapter = cardImgaeAdapter
        binding.card2.adapter = cardImgaeAdapter

        val discountImageAdapter = DiscountImageAdapter()
        binding.lastcard.adapter = discountImageAdapter

        val recommendAdapter  = RecommendAdapter()
        binding.recommendationCard.adapter = recommendAdapter

        binding.reservationButton.setOnClickListener {
            val intent = Intent(binding.root.context, StoreReservationDetailActivity::class.java)
            startActivity(intent)
            // ContextCompat.startActivity(binding.root.context, intent, null)


        }
       binding.myReservation.setOnClickListener {
            val intent = Intent(binding.root.context, StoreReservationActivity::class.java)
            startActivity(intent)
           val shared = getSharedPreferences("reservationInform", Context.MODE_PRIVATE)
           val editor: SharedPreferences.Editor = shared.edit()

           editor.clear()
           editor.commit()
        }

    }
    fun foodListfunc(): ArrayList<Int> {
        val foodList = arrayListOf<Int>(
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food5
        )
        return foodList
    }
}

