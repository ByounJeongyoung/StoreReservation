package com.jeongyoung.sw_reservation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jeongyoung.sw_reservation.LocationActivity.LocationActivity
import com.jeongyoung.sw_reservation.MainFragment.FragmentActivity
import com.jeongyoung.sw_reservation.databinding.ActivityMainBinding
import com.jeongyoung.sw_reservation.location.LoginActivity
import com.jeongyoung.sw_reservation.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var  userDB : DatabaseReference
    private  lateinit var auth: FirebaseAuth
    var user1 = mutableMapOf<String,Any>()



    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)  //xml binding
        setContentView(binding.root)

        userDB = Firebase.database.reference.child("Users")
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if(currentUser !=null){
            val id = "ios@google.com"
            if (currentUser.email == id) {
                startActivity(Intent(this, FragmentActivity::class.java))
            }else {
                showNameInputPopup()

            }
        }



        val foodData = foodListfunc()           //첫번째 layout에 담을 사진 list를 함수로 생성

        val topViewPagerAdapter = TopViewPagerAdapter(foodData) //adapter와 연결
        binding.topLayout.adapter = topViewPagerAdapter


        val middleLayoutAdapter = middleLayoutAdapter()       //두번째 뷰페이저와 어답터 생성,연결
        binding.apply{
            listLayout.adapter = middleLayoutAdapter
            listLayout.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL, false
            )
        }


        val cardImgaeAdapter = CardImgaeAdapter()             //카드 이미지 어답터 생성,연결
        binding.apply{
            card.adapter = cardImgaeAdapter
            card2.adapter = cardImgaeAdapter
        }

        val discountImageAdapter = DiscountImageAdapter()     //할인 이미지 어답터 생성, 연결
        binding.lastcard.adapter = discountImageAdapter

        val recommendAdapter = RecommendAdapter()            //추천 이미지(last)어답터 생성,연결
        binding.recommendationCard.adapter = recommendAdapter

        binding.noId.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //"예약하기" 버튼클릭 -> 예약하는 화면으로 이동
        binding.reservationButton.setOnClickListener {
            if (auth.currentUser == null) {
                Toast.makeText(this, "로그인 후 예약이 가능합니다", Toast.LENGTH_SHORT).show()
            }
        }

        //"내 예약 정보"버튼 클릭-> 내 예약정보 화면으로 이동
        binding.map.setOnClickListener {
            if (auth.currentUser == null) {
                Toast.makeText(this, "로그인 후 예약이 가능합니다", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(binding.root.context, LocationActivity::class.java) ////////////////////////////////////////////////////////////////////////
                startActivity(intent)}
        }
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        if (user == null) {
            binding.noId.isVisible = true

        }
        if (user != null) {
            binding.myInform.isVisible = true
            binding.myInform.setOnClickListener {
                startActivity(Intent(this, StoreReservationActivity::class.java))
            }
        }
        if (user != null) {
            binding.reservationButton.isVisible = true
            binding.reservationButton.setOnClickListener {
                startActivity(Intent(this, LocationActivity::class.java))
            }
        }

        if (user != null) {
            binding.Logout.isVisible = true
            binding.Logout.setOnClickListener {
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        if (user != null) {

            binding.userId.text = user1["name"].toString()

            println(user.tenantId)
            println(user.getIdToken(true))
            println(user.uid)
            Log.d("hellow",""+user.tenantId)
            Log.d("hellow",""+user.getIdToken(true))
            Log.d("hellow",""+user.uid)



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

    }
    private fun showNameInputPopup() {
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("닉네임 입력해주세요")
            .setView(editText)
            .setPositiveButton("저장"){ _, _ ->
                if(editText.text.isEmpty()){
                    showNameInputPopup()
                }else{
                    saveUserName(editText.text.toString())
                }
            }
            .setCancelable(false)
            .show()
    }

    private fun saveUserName(name: String) {
        val userId = auth.currentUser?.uid.orEmpty()
        val currentUserDB = userDB.child(userId)
        user1["userId"] = name
        user1["name"] = name
        currentUserDB.updateChildren(user1)
    }

}


