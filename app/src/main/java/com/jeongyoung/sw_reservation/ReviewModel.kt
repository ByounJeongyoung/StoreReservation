package com.jeongyoung.sw_reservation


data class ReviewModel (
    val id : String,
    val review : String

){
    constructor():this("","")
}