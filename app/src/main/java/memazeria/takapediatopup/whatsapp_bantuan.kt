package memazeria.takapediatopup

import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class whatsapp_bantuan : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var listAdapter: ExpandableListAdapter
    private lateinit var listDataHeader: MutableList<String>
    private lateinit var listDataChild: HashMap<String, List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whatsapp_bantuan)

        expandableListView = findViewById(R.id.expandableListView)

        // Siapkan data grup dan anaknya
        prepareListData()

        listAdapter = YourExpandableListAdapter(this, listDataHeader, listDataChild)
        expandableListView.setAdapter(listAdapter)

        // Listener untuk memunculkan Toast saat anak-anak di klik
        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val childText = listDataChild[listDataHeader[groupPosition]]?.get(childPosition)
            Toast.makeText(
                applicationContext,
                "Clicked: $childText",
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    private fun prepareListData() {
        listDataHeader = mutableListOf()
        listDataChild = HashMap()

        // Tambahkan header/grup
        listDataHeader.add("Bagaimana cara top up?")
        listDataHeader.add("Mengapa memilih Takapedia untuk top up?")

        // Tambahkan child/anak untuk "Bagaimana cara top up?"
        val topUpExplanation = listOf(
            "1. Klik tombol pesan lalu kamu akan diarahkan langsung ke Whatsapp Admin",
            "2. Ketik User ID dan Zone ID.",
            "3. Ketik jumlah diamond yang kamu inginkan.",
            "4. Lalu bayar sesuai harga diamond kamu di price list!.",
            "Menyelesaikan transaksi-mu dan diamond akan langsung terkirim ke akun MLBB-mu.",
            "Kamu juga bisa kirim Diamonds ke teman dan keluarga kamu dengan cara ketik user ID dan Zone ID mereka."
        )
        listDataChild[listDataHeader[0]] = topUpExplanation

        // Tambahkan child/anak untuk "Mengapa memilih Takapedia untuk top up?"
        val whyChooseTakapedia = listOf(
            "1. Mudah & cepat. Kamu hanya perlu hitungan detik untuk menyelesaikan pembelian di Codashop.",
            "2. Garansi sampai. Semua pembelian dijamin langsung masuk ke akun Kamu.",
            "3. Metode pembayaran mudah. Bayar dengan metode pembayaran terpopuler di Indonesia.",
            "4. Customer support kelas dunia. Tim customer support Kami selalu siap membantu 24 jam. Kirim pesanmu melalui form berikut, dan Kami akan merespons segera!"
        )
        listDataChild[listDataHeader[1]] = whyChooseTakapedia
    }
}
