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

        btnMlbb.setOnClickListener {
            Intent(this, activity_ml::class.java).also {
                startActivity(it)
            }
        }
    }
}