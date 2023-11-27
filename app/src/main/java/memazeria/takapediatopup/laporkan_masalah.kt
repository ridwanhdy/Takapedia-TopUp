package memazeria.takapediatopup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class laporkan_masalah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporkan_masalah)

        val btnLapor = findViewById<Button>(R.id.buttonbantuan)

        btnLapor.setOnClickListener {
            val url = "https://wa.link/dlzu85"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}