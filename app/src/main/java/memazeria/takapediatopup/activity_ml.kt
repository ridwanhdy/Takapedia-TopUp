package memazeria.takapediatopup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import memazeria.takapediatopup.data.AppDatabase
import memazeria.takapediatopup.data.dao.DiamondDao
import memazeria.takapediatopup.data.entity.Diamond

class activity_ml : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var diamondDao: DiamondDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ml)
        val btnMlbb = findViewById<Button>(R.id.btnPesanMl)

        diamondDao = AppDatabase.getDatabase(this).DiamondDao()

        // Pricelist
        val pricelist = listOf(
            Pair(60, 12000),
            Pair(330, 60000),
            Pair(1090, 180000),
            Pair(2240, 360000),
            Pair(3880, 600000),
            Pair(8080, 1200000)
            // Anda bisa menambahkan lebih banyak lagi sesuai kebutuhan
        )

        // Insert pricelist to database
        for ((count, price) in pricelist) {
            val diamond = Diamond(count = count, price = price)
            insertDiamondIfNotExists(diamond)
        }

        displayDiamonds()

        btnMlbb.setOnClickListener {
            val url = "https://wa.link/0y8u1l"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun displayDiamonds() {
        CoroutineScope(Dispatchers.IO).launch {
            // Mendapatkan data dari Room Database
            val diamonds = diamondDao.getAllDiamonds().take(6)

            // Menetapkan data ke TextView
            val stringBuilder = StringBuilder()
            for (diamond in diamonds) {
                stringBuilder.append("💎${diamond.count} = Rp ${diamond.price}\n")
            }

            // Menggunakan Main dispatcher untuk memperbarui UI
            withContext(Dispatchers.Main) {
                findViewById<TextView>(R.id.readdiamond1).text = stringBuilder.toString()
            }
        }
    }

    private fun insertDiamondIfNotExists(diamond: Diamond) {
        CoroutineScope(Dispatchers.IO).launch {
            // Memeriksa apakah diamond dengan count yang sama sudah ada
            val existingDiamond = diamondDao.getAllDiamonds().find { it.count == diamond.count }

            if (existingDiamond == null) {
                // Jika tidak ada, maka insert
                diamondDao.insertDiamond(diamond)
            }
        }
    }
}
