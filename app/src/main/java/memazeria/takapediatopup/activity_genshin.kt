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
import memazeria.takapediatopup.data.dao.GensinDiamondDao
import memazeria.takapediatopup.data.entity.GensinDiamond

class activity_genshin : AppCompatActivity() {

    private lateinit var gensinDiamondDao: GensinDiamondDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genshin)

        gensinDiamondDao = AppDatabase.getDatabase(this).GensinDiamondDao()

        // Pricelist Genshin Impact
        val pricelistGenshin = listOf(
            Pair(70, 10000),
            Pair(425, 55000),
            Pair(1000, 120000),
            Pair(1450, 185000),
            Pair(2180, 288000),
            Pair(36500, 4700000),
            Pair(73100, 9245000)
            // Add more entries as needed
        )

        // Insert pricelist to database
        for ((count, price) in pricelistGenshin) {
            val diamond = GensinDiamond(count = count, price = price)
            insertDiamondIfNotExists(diamond)
        }

        displayDiamonds()

        val btnGenshin = findViewById<Button>(R.id.btnPesanGenshin)

        btnGenshin.setOnClickListener {
            val url = "https://wa.link/lyjojo"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun displayDiamonds() {
        CoroutineScope(Dispatchers.IO).launch {
            // Get data from Room Database
            val diamonds = gensinDiamondDao.getAllFfDiamonds()

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

    private fun insertDiamondIfNotExists(diamond: GensinDiamond) {
        CoroutineScope(Dispatchers.IO).launch {
            // Check if a diamond with the same count already exists
            val existingDiamond = gensinDiamondDao.getAllFfDiamonds().find { it.count == diamond.count }

            if (existingDiamond == null) {
                // If it doesn't exist, then insert
                gensinDiamondDao.insertFfDiamond(diamond)
            }
        }
    }
}
