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

    private lateinit var userDB: DatabaseReference
    private lateinit var auth: FirebaseAuth


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
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val id = "soft@naver.com"
            if (currentUser.email == id) {
                startActivity(Intent(this, FragmentActivity::class.java))
            }

        }

        val foodData = foodListfunc()           //????????? layout??? ?????? ?????? list??? ????????? ??????

        val topViewPagerAdapter = TopViewPagerAdapter(foodData) //adapter??? ??????
        binding.topLayout.adapter = topViewPagerAdapter


        val middleLayoutAdapter = middleLayoutAdapter()       //????????? ??????????????? ????????? ??????,??????
        binding.apply {
            listLayout.adapter = middleLayoutAdapter
            listLayout.layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL, false
            )
        }


        val cardImgaeAdapter = CardImgaeAdapter()             //?????? ????????? ????????? ??????,??????
        binding.apply {
            card.adapter = cardImgaeAdapter
            card2.adapter = cardImgaeAdapter
        }
        val discountImageAdapter = DiscountImageAdapter()     //?????? ????????? ????????? ??????, ??????
        binding.lastcard.adapter = discountImageAdapter

        val recommendAdapter = RecommendAdapter()            //?????? ?????????(last)????????? ??????,??????
        binding.recommendationCard.adapter = recommendAdapter

        binding.noId.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //"????????????" ???????????? -> ???????????? ???????????? ??????
        binding.reservationButton.setOnClickListener {
            if (auth.currentUser == null) {
                Toast.makeText(this, "????????? ??? ????????? ???????????????", Toast.LENGTH_SHORT).show()
            }
        }

        //"??? ?????? ??????"?????? ??????-> ??? ???????????? ???????????? ??????
        binding.map.setOnClickListener {
            if (auth.currentUser == null) {
                Toast.makeText(this, "????????? ??? ????????? ???????????????", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(
                    binding.root.context,
                    LocationActivity::class.java
                ) ////////////////////////////////////////////////////////////////////////
                startActivity(intent)
            }
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
            //????????? ?????? ?????????
            val dotString: String = user.email.toString()
            val splitArray = dotString.split(".")
            val emailString: String = splitArray[0].toString()
            val splitNameArray = emailString.split("@")
            binding.userId.text = "${(splitNameArray[0])}???  ???????????????!"

            println(user.tenantId)
            println(user.getIdToken(true))
            println(user.uid)
            Log.d("hellow", "" + user.tenantId)
            Log.d("hellow", "" + user.getIdToken(true))
            Log.d("hellow", "" + user.uid)


        }
        binding.navButton.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START)
        }


    }

    //????????? layout??? ?????? ??????  ?????????
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

        if (currentUser != null) {
            val id = "student@naver.com"
            if (currentUser.email == id) {
                startActivity(Intent(this, FragmentActivity::class.java))
            }

        }

    }
//    private fun showNameInputPopup() {
//
//        AlertDialog.Builder(this)
//            .setTitle("????????? ??????????????????")
//            .setView(editText)
//            .setPositiveButton("??????"){ _, _ ->
//                if(editText.text.isEmpty()){
//                    showNameInputPopup()
//                }else{
//                    saveUserName(editText.text.toString())
//                }
//            }
//            .setCancelable(false)
//            .show()
//    }
//
//    private fun saveUserName(name: String) {
//        val userId = auth.currentUser?.uid.orEmpty()
//        val currentUserDB = userDB.child(userId)
//        user1["userId"] = name
//        user1["name"] = name
//        currentUserDB.updateChildren(user1)
//    }

}


