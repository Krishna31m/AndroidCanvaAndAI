package com.krishna.firebasesetup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Use a Handler to delay the screen transition. [1, 4]
        Handler(Looper.getMainLooper()).postDelayed({
            // Check if the user is already logged in. [14]
            val currentUser = auth.currentUser
            if (currentUser != null) {
                // User is logged in, navigate to MainActivity.
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                // User is not logged in, navigate to LoginActivity.
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
