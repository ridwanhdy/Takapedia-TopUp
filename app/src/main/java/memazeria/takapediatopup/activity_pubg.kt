package memazeria.takapediatopup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activity_pubg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pubg)


        val btnPubg = findViewById<Button>(R.id.btnPesanPubg)

        btnPubg.setOnClickListener {
            val url = "https://wa.link/vq1agd"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }




    }
}