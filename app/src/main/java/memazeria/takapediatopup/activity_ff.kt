package memazeria.takapediatopup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import memazeria.takapediatopup.data.AppDatabase
import memazeria.takapediatopup.data.dao.DiamondDao
import memazeria.takapediatopup.data.dao.FfDiamondDao
import memazeria.takapediatopup.data.entity.Diamond
import memazeria.takapediatopup.data.entity.FfDiamond

class activity_ff : AppCompatActivity() {

    private lateinit var diamondDao: FfDiamondDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ff)

        diamondDao = AppDatabase.getDatabase(this).FfDiamondDao()

        // Pricelist Free Fire
        val pricelistFF = listOf(
            Pair(70, 10000),
            Pair(425, 55000),
            Pair(1000, 120000),
            Pair(1450, 185000),
            Pair(2180, 288000),
            Pair(36500, 4700000),
            Pair(73100, 9245000)
            // Anda bisa menambahkan lebih banyak lagi sesuai kebutuhan
        )

        // Insert pricelist to database
        for ((count, price) in pricelistFF) {
            val diamond = FfDiamond(count = count, price = price)
            insertDiamondIfNotExists(diamond)
        }

        displayDiamonds()

        val btnFf = findViewById<Button>(R.id.btnPesanFf)
        btnFf.setOnClickListener {
            val url = "https://wa.link/8p497g"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun displayDiamonds() {
        CoroutineScope(Dispatchers.IO).launch {
            // Mendapatkan data dari Room Database
            val diamonds = diamondDao.getAllFfDiamonds()

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

    private fun insertDiamondIfNotExists(diamond: FfDiamond) {
        CoroutineScope(Dispatchers.IO).launch {
            // Memeriksa apakah diamond dengan count yang sama sudah ada
            val existingDiamond = diamondDao.getAllFfDiamonds().find { it.count == diamond.count }

            if (existingDiamond == null) {
                // Jika tidak ada, maka insert
                diamondDao.insertFfDiamond(diamond)
            }
        }
    }
}
