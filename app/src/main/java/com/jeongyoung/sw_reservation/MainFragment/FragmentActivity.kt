package com.jeongyoung.sw_reservation.MainFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jeongyoung.sw_reservation.R
import com.jeongyoung.sw_reservation.mypage.MyPageFragment
import com.jeongyoung.sw_reservation.databinding.ActivityFragmentBinding
import com.jeongyoung.sw_reservation.home.HomeFragment
import com.jeongyoung.sw_reservation.reservation.ReservationFragment

class FragmentActivity : AppCompatActivity() {

    val binding by lazy { ActivityFragmentBinding.inflate(layoutInflater) }

    //val locationFragment by lazy { LocationFragment() }
    val myPageFragment by lazy { MyPageFragment() }
    val reservationFragment by lazy { ReservationFragment() }
    val homeFragment by lazy { HomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragment(homeFragment)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                // R.id.location -> replaceFragment(locationFragment)
                R.id.reservation -> replaceFragment(reservationFragment)
                R.id.mypage -> replaceFragment(myPageFragment)
            }
            true
        }
    }
//프래그먼트 대체 함수
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.FrameLayout, fragment)
                commit()
            }
    }
}