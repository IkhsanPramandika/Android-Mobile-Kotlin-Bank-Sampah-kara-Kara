package com.example.uas_mobile.database

import android.os.Parcel
import android.os.Parcelable

class modelDatabase(
    var id: String = "",
    var namaPengguna: String = "",
    var jenisSampah: String = "",
    var berat: Int = 0,
    var harga: Int = 0,
    var tanggal: String = "",
    var alamat: String = "",
    var catatan: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(namaPengguna)
        parcel.writeString(jenisSampah)
        parcel.writeInt(berat)
        parcel.writeInt(harga)
        parcel.writeString(tanggal)
        parcel.writeString(alamat)
        parcel.writeString(catatan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<modelDatabase> {
        override fun createFromParcel(parcel: Parcel): modelDatabase {
            return modelDatabase(parcel)
        }

        override fun newArray(size: Int): Array<modelDatabase?> {
            return arrayOfNulls(size)
        }
    }
}
