package com.jeongyoung.sw_reservation.LocationActivity

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

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap


    val binding by lazy { ActivityLocationBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mapView = findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)


        val storeData = store()
        val locationActivtyAdapter = LocationActivityAdapter(storeData)
        binding.include.recyclerView.adapter = locationActivtyAdapter
        binding.include.recyclerView.layoutManager = LinearLayoutManager(this)


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

        naverMap.maxZoom = 18.0  //용량 확보
        naverMap.minZoom = 10.0

        //시작위티 경기대 구글맵으로 경도 찾기기
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.299489, 127.039043))
        naverMap.moveCamera(cameraUpdate)

        marker(37.29980879050117, 127.04424705024809, "서브웨이")
        marker(37.297835350166174, 127.04258515610323, "카타르시스")
        marker(37.29970341392781, 127.04282601032713, "고도리")
        marker(37.2977970304239, 127.04370512824431, "사쿠라")
        marker(37.29744257188249, 127.04281396761593, "서브웨이")
        marker(37.300632638621764, 127.04506595460931, "맛집")
        marker(37.29858258127691, 127.0452465952867, "롯데리아")
        marker(37.29858258127691, 127.0452465952867, "역전할머니")
        marker(37.29831434533585, 127.04581260271284, "곱창")
        marker(37.298390984235205, 127.04557174847949, "새마을식당")
        marker(37.29876459797781, 127.04580056000164, "정경아식당")
        marker(37.299933326261154, 127.0464147382631, "황금돈까스")
        marker(37.29713601179695, 127.04513821087646, "유가네")
/*
        37.297835350166174, 127.04258515610323
        37.29970341392781, 127.04282601032713
        37.298553841718984, 127.0435365302876
        37.2977970304239, 127.04370512824431
        37.298956194029216, 127.04536702239861
        37.29744257188249, 127.04281396761593
        37.300632638621764, 127.04506595460931
        37.29858258127691, 127.0452465952867
        37.29858258127691, 127.0452465952867
        37.29831434533585, 127.04581260271284
        37.298390984235205, 127.04557174847949
        37.29876459797781, 127.04580056000164
        37.29750963177322, 127.04580056000164
        37.299933326261154, 127.0464147382631
        37.29713601179695, 127.04513821087646
*/
    /*  레트로핏 ing

         val retrofit = Retrofit.Builder()
             .baseUrl("https://run.mocky.io/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()

         retrofit.create(StoreService::class.java).also {
             it.stores().enqueue(object : Callback<StoreRepository> {

                 override fun onResponse(
                     call: Call<StoreRepository>,
                     response: Response<StoreRepository>
                 ) {
                     if (response.isSuccessful.not()) { //싷패처리
                         return

                     }
                     response.body()?.let { storeRepository ->
                         locationActivtyAdapter.submitList(storeRepository.storeList)
                     }
                 }
                 override fun onFailure(call: Call<StoreRepository>, t: Throwable) {
                 }
             })
         }*/
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

val storeList = arrayListOf<Store>()

fun store(): ArrayList<Store> {

    list("카타르시스", "경기대 학생 10000원할인", "Zzzzzz")
    list("역전할머니", "인스타 태그 시 음료수 1개 무료", "Zzzzzz")
    list("서브웨이", "경기대 학생 2000원 할인", "Zzzzzz")
    list("고도리 비스트로", "소주 3병 -> 1병 무료", "Zzzzzz")
    list("투썸", "00", "00")
    list("스타벅스", "00", "00")
    list("투썸", "00", "00")
    list("스타벅스", "00", "00")
    list("카타르시스", "경기대 학생 10000원할인", "Zzzzzz")
    list("역전할머니", "인스타 태그 시 음료수 1개 무료", "Zzzzzz")
    list("서브웨이", "경기대 학생 2000원 할인", "Zzzzzz")
    list("고도리 비스트로", "소주 3병 -> 1병 무료", "Zzzzzz")


    return storeList
}

fun list(name: String, price: String, titlefood: String) {
    val store = Store(name, price, titlefood)
    storeList.add(store)
}

data class Store(var name: String, val price: String, val titlefood: String)

/*
fun store1(title:String,price: String,titlefood: String) {
    val title = title
    val price = price
    val titlefood = titlefood

return Store(title, price, titlefood) as Store
}*/
/*
    override fun onClick(overlay: Overlay): Boolean {

        overlay.tag.let {
              Snackbar.make (baseContext,"fdsdf",Toast.LENGTH_SHORT).show()
        }
     return false
    }
}*/





