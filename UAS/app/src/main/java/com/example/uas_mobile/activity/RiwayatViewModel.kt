import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.uas_mobile.database.modelDatabase

class RiwayatViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    // LiveData for real-time updates
    private val _totalSaldo = MutableLiveData<Int>()
    val totalSaldo: LiveData<Int> = _totalSaldo

    private val _dataBank = MutableLiveData<List<modelDatabase>>()
    val dataBank: LiveData<List<modelDatabase>> = _dataBank

    init {
        // Example path in Firebase Database
        val dataBankRef = databaseReference.child("modelDatabase")
        // Listen for changes in dataBankRef
        dataBankRef.addValueEventListener(object :  ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<modelDatabase>()
                for (dataSnapshot in snapshot.children) {
                    val model = dataSnapshot.getValue(modelDatabase::class.java)
                    model?.let { dataList.add(it) }
                }
                _dataBank.value = dataList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Example path in Firebase Database
        val saldoRef = databaseReference.child("totalSaldo")
        // Listen for changes in saldoRef
        saldoRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val total = snapshot.getValue(Int::class.java)
                _totalSaldo.value = total
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    // Function to delete data by ID
    fun deleteDataById(uid: String) {
        // Example path in Firebase Database
        val dataBankRef = databaseReference.child("modelDatabase")
        dataBankRef.child(uid).removeValue()
            .addOnSuccessListener {
                // Data deleted successfully
            }
            .addOnFailureListener {
                // Failed to delete data
            }
    }
}
