package com.jeongyoung.sw_reservation.reservation

data class ReservationModel (

    val name : String,
    val people: String,
    val crateAT : Long, //시간

){
    constructor():this("","",0)
}