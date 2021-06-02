package com.jeongyoung.sw_reservation.LocationActivity

import retrofit2.Call
import retrofit2.http.GET
//Retrofit 인터페이스
interface StoreService {
    @GET("v3/032ecbdd-2ec8-42bb-8607-87746a922764")
    fun stores(): Call<StoreRepository>
}
//