package com.example.uas_mobile.database

class User (
    val id :String,
    val username:String,
    val address:String,
    val email:String,
    val password:String,

) {
    constructor() : this ("" ,"","","","")
}


