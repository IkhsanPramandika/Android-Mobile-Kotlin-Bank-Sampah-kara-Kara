package com.example.uas_mobile

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_mobile.database.modelDatabase

class RiwayatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val textViewTvNama: TextView = itemView.findViewById(R.id.tvNama)
    val textViewTvData: TextView = itemView.findViewById(R.id.tvDate)
    val textViewTvKategori: TextView = itemView.findViewById(R.id.tvKategori)
    val textViewTvBerat: TextView = itemView.findViewById(R.id.tvBerat)
    val textViewTvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    val textViewTvSaldo: TextView = itemView.findViewById(R.id.tvSaldo)

//    fun bind(product: modelDatabase, onItemClickListener: RiwayatAdapter.OnItemClickListener)
//    {
//        itemView.setOnClickListener {
//           onItemClickListener.onItemClick(product)
//       }
//
//        // Populate other views
//        // ...
//    }
}