package memazeria.takapediatopup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activity_genshin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genshin)


        val btnGenshin = findViewById<Button>(R.id.btnPesanGenshin)

        btnGenshin.setOnClickListener {
            val url = "https://wa.link/r87cya"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }



    }
}