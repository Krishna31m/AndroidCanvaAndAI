package com.krishna.transition

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnCW = findViewById<Button>(R.id.btnCW)
        val btnACW = findViewById<Button>(R.id.btnACW)
        val btnScale = findViewById<Button>(R.id.btnScale)

        btnCW.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.cw)
            imageView.startAnimation(anim)
        }

        btnACW.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.acw)
            imageView.startAnimation(anim)
        }


        btnScale.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.scale_center)
            imageView.startAnimation(anim)
        }

    }
}
