package com.jeongyoung.sw_reservation

import com.jeongyoung.sw_reservation.LocationActivity.LocationActivityAdapter
import com.jeongyoung.sw_reservation.databinding.ActivityReservationBinding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jeongyoung.sw_reservation.MainActivity
import com.jeongyoung.sw_reservation.R
import com.jeongyoung.sw_reservation.TopViewPagerAdapter
import com.jeongyoung.sw_reservation.databinding.ActivityLocationBinding
import com.jeongyoung.sw_reservation.databinding.ActivityMainBinding
import com.jeongyoung.sw_reservation.databinding.StoreReservationMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreReservationMapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap

    val binding by lazy { StoreReservationMapBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(map: NaverMap) {

        naverMap = map

        //용량 확보
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 21.0

        //시작위치 고도리 위치로 네이버맵으로 경도 초기화
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.29970341392781, 127.04282601032713))
        naverMap.moveCamera(cameraUpdate)
        marker(37.29970341392781, 127.04282601032713, "고도리")
    }

    fun marker(lat: Double, long: Double, storeName: String) {
        val marker = Marker()
        with(marker) {
            position = LatLng(lat, long)
            map = naverMap
            icon = MarkerIcons.BLACK
            iconTintColor = Color.RED
            captionText = storeName
        }
    }
}



