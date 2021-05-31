package com.jeongyoung.sw_reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
    private lateinit var selected:String
    private lateinit var lunchTime : String
    private lateinit var dinnerTime : String


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
private var dayOrNight = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityReservationDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val intent = Intent()
//        val name = intent.getStringExtra("storeName")
//        val img =intent.getIntExtra("storeImage",0)
//        Log.d("name",""+name)
//          binding.storeName.text = name
//        binding.storeImg.setImageResource(img)


        //데이터 중복 방지
        reservationList.clear()
        //추가할 데이터 위치 설정
        articleDB = Firebase.database.reference.child(DBkey.DB_ARTICLES)
        binding.nameEditText3.isVisible  = false
        binding.nameEditText4.isVisible = false

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
              R.id.radioLunch -> {
                  binding.nameEditText3.isVisible  = true
                  binding.nameEditText4.isVisible = false
                  dayOrNight = true
              }
              R.id.radioNight -> {
                  binding.nameEditText3.isVisible = false
                  binding.nameEditText4.isVisible  = true
                  dayOrNight = false
                 }
            }
        }

        var numberData = listOf("-선택하세요","1명","2명","3명","4명")
        var timeLunchData = listOf("-오전-","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30")
        var timeDinnerData = listOf("-오후-","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30")

        var numberDataAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,numberData)
        var timeLunchDataAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,timeLunchData)
        var timeDinnerDataAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,timeDinnerData)
        with(binding) {
            nameEditText2.adapter = numberDataAdapter
            nameEditText3.adapter = timeLunchDataAdapter
            nameEditText4.adapter = timeDinnerDataAdapter

            nameEditText2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
             ) {
            selected = numberData.get(position)
               }override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
             nameEditText3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                lunchTime = timeLunchData.get(position)
                    Log.d("lunch","$lunchTime")
                }override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
            nameEditText4.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    dinnerTime = timeDinnerData.get(position)
                    Log.d("night","$dinnerTime")
                }override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
}
        //버튼 클릭시 데이터 추가 listener
         binding.button222.setOnClickListener {
            articleDB.addChildEventListener(listener)

            val storeName = binding.nameEditText1.text.toString()
            val peopleNum = selected
             var reserveTime =lunchTime
             if(dayOrNight == false){reserveTime = dinnerTime


             }

            val reservationModel1 = ReservationModel(storeName, peopleNum,reserveTime)

             val shared = getSharedPreferences("storeName",Context.MODE_PRIVATE)
             val editor = shared.edit()
             editor.putString("name",storeName)
             editor.putString("peopleNum",peopleNum)
             editor.putString("reserveTime",reserveTime)
             editor.apply()

            articleDB.push().setValue(reservationModel1)
             finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//    val intent = Intent(this,ActivityMainBinding::class.java)
//        startActivity(intent)
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
