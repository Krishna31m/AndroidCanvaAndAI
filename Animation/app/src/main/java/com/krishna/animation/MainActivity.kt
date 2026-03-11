package com.krishna.animation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnFade = findViewById<Button>(R.id.btnFade)
        val btnMove = findViewById<Button>(R.id.btnMove)
        val btnCoin = findViewById<Button>(R.id.btnCoin)

        btnFade.setOnClickListener {
            // Toggle fade in and fade out
            val anim = AnimationUtils.loadAnimation(this, R.anim.coin_flip)
            imageView.startAnimation(anim)
        }

        btnMove.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.move)
            imageView.startAnimation(anim)
        }

//

        btnCoin.setOnClickListener {

            imageView.cameraDistance = 8000f

            val flip = AnimatorInflater.loadAnimator(this, R.animator.coin_flip)
            flip.setTarget(imageView)

            val move = AnimatorInflater.loadAnimator(this, R.animator.coin_move)
            move.setTarget(imageView)

            val set = AnimatorSet()
            set.playTogether(flip, move)
            set.start()
        }


    }
}