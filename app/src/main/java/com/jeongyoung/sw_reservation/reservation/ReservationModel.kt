package com.jeongyoung.sw_reservation.reservation

data class ReservationModel (

    val name : String,
    val people: String,
    val time : String,
     //시간

){
    constructor():this("","","")
}
