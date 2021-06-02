package com.jeongyoung.sw_reservation


data class ReviewModel (
    val id : String,
    val review : String,
    val score : Double

){
    constructor():this("","",0.0)
}//