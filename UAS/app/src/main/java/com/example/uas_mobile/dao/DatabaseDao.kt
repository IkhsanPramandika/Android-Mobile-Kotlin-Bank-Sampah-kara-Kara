//package com.example.uas_mobile.dao
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.uas_mobile.database.modelDatabase
//
//@Dao
//interface DatabaseDao {
//
//    @Query("SELECT * FROM tbl_banksampah")
//    fun getAll(): LiveData<List<modelDatabase>>
//
//    @Query("SELECT SUM(harga) FROM tbl_banksampah")
//    fun getSaldo(): LiveData<Int>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertData(modelDatabase: modelDatabase)
//
//    @Query("DELETE FROM tbl_banksampah WHERE uid= :uid")
//    fun deleteSingleData(uid: Int)
//}
