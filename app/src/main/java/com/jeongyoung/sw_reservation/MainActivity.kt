package com.jeongyoung.sw_reservation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jeongyoung.sw_reservation.LocationActivity.LocationActivity
import com.jeongyoung.sw_reservation.MainFragment.FragmentActivity
import com.jeongyoung.sw_reservation.databinding.ActivityMainBinding
import com.jeongyoung.sw_reservation.location.LoginActivity

class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding  = ActivityMainBinding.inflate(layoutInflater)  //xml binding
        setContentView(binding.root)

        val foodData = foodListfunc()           //첫번째 layout에 담을 사진 list를 함수로 생성

        val topViewPagerAdapter = TopViewPagerAdapter(foodData) //adapter와 연결
        binding.topLayout.adapter = topViewPagerAdapter


        val middleLayoutAdapter = middleLayoutAdapter()       //두번째 뷰페이저와 어답터 생성,연결
        binding.listLayout.adapter = middleLayoutAdapter
        binding.listLayout.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.HORIZONTAL, false
        )

        val cardImgaeAdapter = CardImgaeAdapter()             //카드 이미지 어답터 생성,연결
        binding.card.adapter = cardImgaeAdapter
        binding.card2.adapter = cardImgaeAdapter

        val discountImageAdapter = DiscountImageAdapter()     //할인 이미지 어답터 생성, 연결
        binding.lastcard.adapter = discountImageAdapter

        val recommendAdapter  = RecommendAdapter()            //추천 이미지(last)어답터 생성,연결
        binding.recommendationCard.adapter = recommendAdapter

        //"예약하기" 버튼클릭 -> 예약하는 화면으로 이동
        binding.reservationButton.setOnClickListener {
            if(auth.currentUser == null){
                Toast.makeText(this,"로그인 후 예약이 가능합니다",Toast.LENGTH_SHORT).show()
            }
        }

        //"내 예약 정보"버튼 클릭-> 내 예약정보 화면으로 이동
       binding.myReservation.setOnClickListener {
            val intent = Intent(binding.root.context, StoreReservationActivity::class.java)
            startActivity(intent)
        }

        binding.Logout.setOnClickListener {
            auth.signOut()
                startActivity(Intent(this,LoginActivity::class.java))

        }
        binding.navButton.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START)
        }
    }

    //첫번째 layout에 담을 사진  함수화
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
   override fun onStart() {
      super.onStart()
        auth = Firebase.auth
      val currentUser = auth.currentUser
      /*if (currentUser == null) {
          startActivity(Intent(this,LoginActivity::class.java))
      }*/
  }


}


