package memazeria.takapediatopup

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class splashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.videoView)

        val path =
            "android.resource://" + packageName + "/" + R.raw.splash

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.splash ))
        videoView.start()

        videoView.setOnCompletionListener { mp: MediaPlayer? ->
            startActivity(Intent(this@splashActivity, LoginActivity::class.java))

        }
    }
}
