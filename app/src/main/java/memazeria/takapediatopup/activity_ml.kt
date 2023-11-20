package memazeria.takapediatopup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class activity_ml : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ml)

        val btnMlbb = findViewById<Button>(R.id.btnPesanMl)

        btnMlbb.setOnClickListener {
            val url = "https://wa.link/0y8u1l"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }


    }
}