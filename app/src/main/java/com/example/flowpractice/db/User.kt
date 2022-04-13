package com.example.flowpractice.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(uid:Int,firstName: String, lastName: String){

    @PrimaryKey
    var uid:Int = uid

    @ColumnInfo (name = "firstName")
    var firstName:String = firstName

    @ColumnInfo (name = "lastName")
    var lastName:String = lastName

}
