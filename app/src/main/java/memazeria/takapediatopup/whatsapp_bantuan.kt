package memazeria.takapediatopup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class whatsapp_bantuan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whatsapp_bantuan)

        val btnWhatsappChat = findViewById<Button>(R.id.btn_whatsapp_chat)
        btnWhatsappChat.setOnClickListener {
            whatsappChatBantuan("6281234567890")
        }
    }

    fun whatsappChatBantuan(namaPengguna: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://wa.me/$namaPengguna")
        startActivity(intent)
    }
}