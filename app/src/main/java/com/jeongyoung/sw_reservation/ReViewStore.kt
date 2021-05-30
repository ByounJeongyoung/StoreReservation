package com.jeongyoung.sw_reservation

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import com.jeongyoung.sw_reservation.databinding.ActivityReviewBinding
import com.jeongyoung.sw_reservation.databinding.FragmentReservationBinding
import com.jeongyoung.sw_reservation.reservation.DBkey
import com.jeongyoung.sw_reservation.reservation.ReservationAdapter
import com.jeongyoung.sw_reservation.reservation.ReservationModel


class ReViewStore : AppCompatActivity() {
    private lateinit var articleDB: DatabaseReference
    private lateinit var reviewAdapter: ReviewAdapter

    private val ReviewModelList = mutableListOf<ReviewModel>()


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
        val binding = ActivityReviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ReviewModelList.clear()
        reviewAdapter = ReviewAdapter()
        articleDB = Firebase.database.reference.child(DB_REVIEWS)
        binding.reviewRecyclerView.adapter = reviewAdapter
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.storeReservationDetailActivity.setOnClickListener {
            startActivity(Intent(this@ReViewStore,StoreReservationDetailActivity::class.java))
        }
        articleDB.addChildEventListener(listener)

    }

    override fun onResume() {
        super.onResume()
        reviewAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        articleDB.removeEventListener(listener)
    }
}
