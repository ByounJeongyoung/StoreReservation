package com.jeongyoung.sw_reservation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jeongyoung.sw_reservation.R
import com.jeongyoung.sw_reservation.StoreReservationActivity
import com.jeongyoung.sw_reservation.databinding.FragmentMypageBinding
import com.jeongyoung.sw_reservation.databinding.FragmentReservationBinding
import com.jeongyoung.sw_reservation.location.LoginActivity

class MyPageFragment : Fragment(R.layout.fragment_mypage) {
    private lateinit var auth: FirebaseAuth

    private var binding: FragmentMypageBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        val fragmentReservationBinding = FragmentMypageBinding.bind(view) //뷰아 xml연결
        binding = fragmentReservationBinding
        val user = Firebase.auth.currentUser


        binding!!.logout.setOnClickListener {


                auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }
        if (user != null) {
            user?.let {
                val name = user.email

                binding!!.userId.text = name
            }
        }else{

        }
    }

}