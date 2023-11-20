package memazeria.takapediatopup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnMlbb = findViewById<Button>(R.id.mlbb)
        val btnPubg = findViewById<Button>(R.id.pubg)
        val btnGenshin = findViewById<Button>(R.id.genshin)
        val btnFreeFire = findViewById<Button>(R.id.ff)

        btnMlbb.setOnClickListener {
            Intent(this, activity_ml::class.java).also {
                startActivity(it)
            }
        }

        btnPubg.setOnClickListener {
            Intent(this, activity_pubg::class.java).also {
                startActivity(it)
            }
        }

        btnGenshin.setOnClickListener {
            Intent(this, activity_genshin::class.java).also {
                startActivity(it)
            }
        }

        btnFreeFire.setOnClickListener {
            Intent(this, activity_ff::class.java).also {
                startActivity(it)
            }
        }
    }
}