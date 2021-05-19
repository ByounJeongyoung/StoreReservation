package com.jeongyoung.sw_reservation.reservation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jeongyoung.sw_reservation.MainActivity
import com.jeongyoung.sw_reservation.R
import com.jeongyoung.sw_reservation.databinding.FragmentHomeBinding
import com.jeongyoung.sw_reservation.databinding.FragmentReservationBinding
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
       // articleDB = Firebase.database.reference.child(DB_ARTICLES)

        reservationAdapter = ReservationAdapter(reservationClickedlistener = {
            val intent = Intent(binding!!.root.context, MainActivity::class.java)
            startActivity((intent))
        })
        /* reservationAdapter.submitList(mutableListOf<ReservationModel>().apply {
             for(no in 1..10){
             add(ReservationModel("김길동", "2명", 20210516))
             add(ReservationModel("김철수", "21명", 20210516))
             add(ReservationModel("김수철", "222명", 20210516))
             add(ReservationModel("철수김", "2333명", 20210516))
         }})

         */

        articleDB = Firebase.database.reference.child(DB_ARTICLES) //데이터베이스에서 데이터를 읽거나 쓰려면
        // DatabaseReference의 인스턴스가 필요합니다.


        fragmentReservationBinding.detailRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentReservationBinding.detailRecyclerView.adapter = reservationAdapter

        articleDB.addChildEventListener(listener)


        //   articleAdapter.submitList(mutableListOf<ArticleModel>().apply {
        //     add(ArticleModel("0","aaaaaa",1000,"4000",""))

        //})
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

/*
private val listener = object :ChildEventListener{
    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        val articleModel = snapshot.getValue(ReservationModel::class.java)
        articleModel ?: return
        articleList.add(articleModel)
        reservationAdapter.submitList(articleList)
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
private var binding : FragmentHomeBinding? = null
private val auth:FirebaseAuth by lazy{
    Firebase.auth
}
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val fragmentHomeBinding = FragmentHomeBinding.bind(view) //뷰아 xml연결
    binding = fragmentHomeBinding

    articleList.clear()
    articleDB = Firebase.database.reference.child(DB_ARTICLES) //데이터베이스에서 데이터를 읽거나 쓰려면
    // DatabaseReference의 인스턴스가 필요합니다.

    articleAdapter = ArticleAdapter()
    //   articleAdapter.submitList(mutableListOf<ArticleModel>().apply {
    //     add(ArticleModel("0","aaaaaa",1000,"4000",""))

    //})
    fragmentHomeBinding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
    fragmentHomeBinding.articleRecyclerView.adapter = articleAdapter

    articleDB.addChildEventListener(listener)
}


}*/