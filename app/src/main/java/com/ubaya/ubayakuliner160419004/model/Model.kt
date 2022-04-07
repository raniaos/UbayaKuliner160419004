package com.ubaya.ubayakuliner160419004.model

data class Restaurant(
    val id:Int?,
    var name:String?,
    var review:Double?,
    var description:String?,
    var address:String?,
    var phone_number:String?,
    var distance:Double?,
    var photo:String?,
    var likes: Int?)

data class Menu(
    val id:Int?,
    var name:String?,
    var price:Int?,
    var description:String?,
    var restaurants_id:Int?,
    var photo: String?)

data class Review(
    val id:Int?,
    var review:Int?,
    var comment:String?,
    var date:String?,
    var users_username:String?,
    var restaurants_id:Int?,
    var name: String?,
    var photo:String?)

data class Reservation(
    val id:Int?,
    var time:String?,
    var notes:String?,
    var finished:Int?,
    var users_username:String?,
    var restaurants_id:Int?,
    var name:String?,
    var photo:String?)

data class User(
    val username: String?,
    var password: String?,
    var name: String?,
    var photo: String?
)