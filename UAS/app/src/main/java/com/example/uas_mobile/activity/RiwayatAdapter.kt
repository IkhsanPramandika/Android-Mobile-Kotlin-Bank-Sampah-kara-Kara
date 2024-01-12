import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_mobile.R
import com.example.uas_mobile.database.modelDatabase

class RiwayatAdapter(
    private val context: Context,
    private var dataList: List<modelDatabase>,
    private val callback: RiwayatAdapterCallback
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_riwayat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        // Bind your data to the views in the ViewHolder here
        holder.bindData(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Find your views here
        private val textViewName: TextView = itemView.findViewById(R.id.tvSaldo)
        private val textViewDetails: TextView = itemView.findViewById(R.id.tvStatus)

        fun bindData(data: modelDatabase) {
            // Bind your data to the views here
            textViewName.text = data.namaPengguna
            textViewDetails.text = "${data.jenisSampah}, ${data.harga}"
            // Implement click listeners or any other operations if needed
            itemView.setOnClickListener {
                callback.onItemClick(data)
            }
        }
    }

    // Interface for click handling
    interface RiwayatAdapterCallback {
        fun onItemClick(data: modelDatabase)
        fun onDelete(data: modelDatabase)

    }

    // Method to update adapter data
    fun setDataAdapter(dataList: List<modelDatabase>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}
