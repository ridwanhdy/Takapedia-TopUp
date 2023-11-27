package memazeria.takapediatopup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnMlbb = findViewById<Button>(R.id.mlbb)
        val btnPubg = findViewById<Button>(R.id.pubg)
        val btnGenshin = findViewById<Button>(R.id.genshin)
        val btnFreeFire = findViewById<Button>(R.id.ff)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)


        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_call -> {
                    // Handle click on "Lapor Masalah" button
                    val callIntent = Intent(this, laporkan_masalah::class.java)
                    startActivity(callIntent)
                    true
                }
                R.id.action_message -> {
                    // Handle click on "Bantuan" button
                    val messageIntent = Intent(this, whatsapp_bantuan::class.java)
                    startActivity(messageIntent)
                    true
                }
                R.id.action_question -> {
                    // Handle click on "Profile" button
                    val profileIntent = Intent(this, profile_pengguna::class.java)
                    startActivity(profileIntent)
                    true
                }
                else -> false
            }
        }


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