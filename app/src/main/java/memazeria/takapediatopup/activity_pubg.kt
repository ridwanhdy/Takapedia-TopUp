package memazeria.takapediatopup

import android.annotation.SuppressLint
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
import memazeria.takapediatopup.data.dao.PubgDiamondDao
import memazeria.takapediatopup.data.entity.PubgDiamond

class activity_pubg : AppCompatActivity() {

    private lateinit var pubgDiamondDao: PubgDiamondDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pubg)

        pubgDiamondDao = AppDatabase.getDatabase(this).PubgDiamondDao()

        // Pricelist PUBG
        val pricelistPUBG = listOf(
            Pair(30, 7000),
            Pair(325, 65000),
            Pair(975, 19200),
            Pair(1500, 285000),
            Pair(4180, 688000),
            Pair(8500, 1700000),
            Pair(32100, 9245000)
            // Add more entries as needed
        )

        // Insert pricelist to database
        for ((count, price) in pricelistPUBG) {
            val diamond = PubgDiamond(count = count, price = price)
            insertDiamondIfNotExists(diamond)
        }

        displayDiamonds()

        val btnPubg = findViewById<Button>(R.id.btnPesanPubg)

        btnPubg.setOnClickListener {
            val url = "https://wa.link/vq1agd"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun displayDiamonds() {
        CoroutineScope(Dispatchers.IO).launch {
            // Get data from Room Database
            val diamonds = pubgDiamondDao.getAllPubgDiamonds()

            // Set data to TextView
            val stringBuilder = StringBuilder()
            for (diamond in diamonds) {
                stringBuilder.append("💎${diamond.count} = Rp ${diamond.price}\n")
            }

            // Use Main dispatcher to update UI
            withContext(Dispatchers.Main) {
                findViewById<TextView>(R.id.readdiamond1).text = stringBuilder.toString()
            }
        }
    }

    private fun insertDiamondIfNotExists(diamond: PubgDiamond) {
        CoroutineScope(Dispatchers.IO).launch {
            // Check if a diamond with the same count already exists
            val existingDiamond = pubgDiamondDao.getAllPubgDiamonds().find { it.count == diamond.count }

            if (existingDiamond == null) {
                // If it doesn't exist, then insert
                pubgDiamondDao.insertPubgDiamond(diamond)
            }
        }
    }
}
