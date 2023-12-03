package memazeria.takapediatopup

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class profile_pengguna : AppCompatActivity() {
    private lateinit var textEmail: TextView
    private lateinit var buttonLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_pengguna)

        // Inisialisasi TextView dan Button
        textEmail = findViewById(R.id.textEmail)
        buttonLogout = findViewById(R.id.buttonbantuan)

        // Memanggil fungsi untuk menampilkan email dari Firebase
        displayEmailFromFirebase()

        // Menambahkan onClickListener pada button logout
        buttonLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun displayEmailFromFirebase() {
        // Mendapatkan instance Firebase Auth
        val auth = FirebaseAuth.getInstance()

        // Memeriksa apakah pengguna sedang login
        val user = auth.currentUser
        if (user != null) {
            // Mendapatkan email pengguna dari objek User
            val email = user.email

            // Menampilkan email ke dalam TextView
            textEmail.text = "Email: $email"
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Yakin ingin logout?")
        builder.setPositiveButton("Ya") { _, _ ->
            // Melakukan logout
            logout()
        }
        builder.setNegativeButton("Tidak", null)
        builder.show()
    }

    private fun logout() {
        // Melakukan logout dari Firebase
        FirebaseAuth.getInstance().signOut()

        // Pindah ke activity login
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        finish() // Menutup activity saat ini
    }
}
