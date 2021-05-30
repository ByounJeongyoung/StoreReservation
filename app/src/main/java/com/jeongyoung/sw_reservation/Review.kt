package com.jeongyoung.sw_reservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jeongyoung.sw_reservation.ReviewDBkey.Companion.DB_REVIEWS
import com.jeongyoung.sw_reservation.databinding.ActivityMainBinding
import com.jeongyoung.sw_reservation.databinding.ActivityReservationDetailBinding
import com.jeongyoung.sw_reservation.databinding.ActivityReviewWriteBinding
import com.jeongyoung.sw_reservation.databinding.FragmentReservationBinding
import com.jeongyoung.sw_reservation.reservation.DBkey
import com.jeongyoung.sw_reservation.reservation.ReservationAdapter
import com.jeongyoung.sw_reservation.reservation.ReservationModel

class Review : AppCompatActivity() {
    private lateinit var articleDB: DatabaseReference
    private lateinit var reviewAdapter: ReviewAdapter

    private  lateinit var auth: FirebaseAuth
    private val ReviewModelList = mutableListOf<ReviewModel>()
    val user = Firebase.auth.currentUser

    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val reviewModel = snapshot.getValue(ReviewModel::class.java)
            reviewModel ?: return
             ReviewModelList.add(reviewModel)
            reviewAdapter.submitList(ReviewModelList)
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
        val binding = ActivityReviewWriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //데이터 중복 방지
        ReviewModelList.clear()
        //추가할 데이터 위치 설정
        articleDB = Firebase.database.reference.child(DB_REVIEWS)

        binding.reviewButton.setOnClickListener {
            articleDB.addChildEventListener(listener)

            val comment = binding.comment.text.toString()
            val id = user.email
            val reviewModel = ReviewModel(id,comment)

            articleDB.push().setValue(reviewModel)
            finish()
        }
    }
    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//


//class Review : AppCompatActivity() {
//
//    private lateinit var articleDB: DatabaseReference //나중에 초기화 해줘야함,
//    private lateinit var reviewAdapter: ReviewAdapter
//
//    private val ReviewModelList = mutableListOf<ReviewModel>()
//    private val listener = object : ChildEventListener {
//        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//            val ReviewModel = snapshot.getValue(ReviewModel::class.java)
//            ReviewModel ?: return
//            ReviewModelList.add(com.jeongyoung.sw_reservation.ReviewModel)
//            reviewAdapter.submitList(ReviewModelList)
//        }
//        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//        }
//
//        override fun onChildRemoved(snapshot: DataSnapshot) {
//        }
//
//        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//        }
//
//        override fun onCancelled(error: DatabaseError) {
//        }
//
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = com.jeongyoung.sw_reservation.databinding.ActivityReviewBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//       //뷰아 xml연결
//
//
//
//        ReviewModelList.clear()
//        val reviewAdapter = ReviewAdapter()
//        binding.reviewRecyclerView.adapter = reviewAdapter
//        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(this)
//        // articleDB = Firebase.database.reference.child(DB_ARTICLES)
//        articleDB = Firebase.database.reference.child(DB_REVIEWS) //데이터베이스에서 데이터를 읽거나 쓰려면
//        // DatabaseReference의 인스턴스가 필요합니다.
//
//
////        ReviewAdapter.re       iewRecyclerView.layoutManager = LinearLayoutManager(context)
////        ReviewAdapter.reviewRecyclerView.adapter = reservationAdapter
//
//        articleDB.addChildEventListener(listener)
//
//
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        reservationAdapter.notifyDataSetChanged()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        articleDB.removeEventListener(listener)
//
//
//}
//}
