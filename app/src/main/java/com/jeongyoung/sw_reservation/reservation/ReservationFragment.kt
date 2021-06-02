package com.jeongyoung.sw_reservation.reservation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jeongyoung.sw_reservation.MainActivity
import com.jeongyoung.sw_reservation.R
import com.jeongyoung.sw_reservation.databinding.FragmentHomeBinding
import com.jeongyoung.sw_reservation.databinding.FragmentReservationBinding
import com.jeongyoung.sw_reservation.mypage.MyPageFragment
import com.jeongyoung.sw_reservation.reservation.DBkey.Companion.DB_ARTICLES

class ReservationFragment : Fragment(R.layout.fragment_reservation) {
    private lateinit var articleDB: DatabaseReference //나중에 초기화 해줘야함,
    private lateinit var reservationAdapter: ReservationAdapter

    private val reservationList = mutableListOf<ReservationModel>()

    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val reservationModel = snapshot.getValue(ReservationModel::class.java)
            reservationModel ?: return
            reservationList.add(reservationModel)
            reservationAdapter.submitList(reservationList)
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
    private var binding: FragmentReservationBinding? = null
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentReservationBinding = FragmentReservationBinding.bind(view) //뷰아 xml연결
        binding = fragmentReservationBinding

        reservationList.clear()
        reservationAdapter = ReservationAdapter(reservationClickedlistener = {

        })

        articleDB = Firebase.database.reference.child(DB_ARTICLES) //데이터베이스에서 데이터를 읽거나 쓰려면
        // DatabaseReference의 인스턴스가 필요합니다.
        fragmentReservationBinding.detailRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentReservationBinding.detailRecyclerView.adapter = reservationAdapter
        articleDB.addChildEventListener(listener)
    }
    override fun onResume() {
        super.onResume()
        reservationAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        articleDB.removeEventListener(listener)
    }
}
