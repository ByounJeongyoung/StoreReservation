package com.jeongyoung.sw_reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jeongyoung.sw_reservation.LocationActivity.Store
import com.jeongyoung.sw_reservation.databinding.ActivityMainBinding
import com.jeongyoung.sw_reservation.databinding.ActivityReservationDetailBinding
import com.jeongyoung.sw_reservation.databinding.FragmentReservationBinding
import com.jeongyoung.sw_reservation.reservation.DBkey
import com.jeongyoung.sw_reservation.reservation.ReservationAdapter
import com.jeongyoung.sw_reservation.reservation.ReservationModel
import java.util.*

class StoreReservationDetailActivity : AppCompatActivity() {
    private lateinit var articleDB: DatabaseReference
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


    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityReservationDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //데이터 중복 방지
        reservationList.clear()
        //추가할 데이터 위치 설정
        articleDB = Firebase.database.reference.child(DBkey.DB_ARTICLES)

        //버튼 클릭시 데이터 추가 listener
         binding.button222.setOnClickListener {
            articleDB.addChildEventListener(listener)

            val storeName = binding.nameEditText1.text.toString()
            val peopleNum = binding.nameEditText2.text.toString()
            val reservationModel1 = ReservationModel(storeName, peopleNum, 0)
            articleDB.push().setValue(reservationModel1)
             finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    val intent = Intent(this,ActivityMainBinding::class.java)
        startActivity(intent)
    }
}


/* val shared = getSharedPreferences("reservationInform", Context.MODE_PRIVATE)
 val editor = shared.edit()

 editor.putString("storeName",storeName)
 editor.putString("peopleNum",peopleNum)
 editor.apply()*/
//            val intent = Intent(this,StoreReservationActivity::class.java)
//      intent.putExtra("people",storeName)
//    intent.putExtra("peopleNum",peopleNum)


//     finish()

//articleDB.child("3번째").child("name").setValue(store)
//articleDB.child("last").child("people").setValue("100명")
