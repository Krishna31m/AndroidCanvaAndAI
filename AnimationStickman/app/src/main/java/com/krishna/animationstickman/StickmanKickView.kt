package com.krishna.animationstickman

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class StickmanKickView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 8f
        color = Color.BLACK
        strokeCap = Paint.Cap.ROUND
    }

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    private val shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.argb(60, 0, 0, 0)
    }

    private var groundY = 0f
    private var headRadius = 40f
    private var bodyLength = 120f
    private var legLength = 120f
    private var armLength = 90f
    private var ballRadius = 30f

    private var stickmanX = 0f
    private var stickmanY = 0f

    private var legAngle = 0f
    private var ballX = 0f
    private var ballY = 0f

    private var isAnimating = false

    fun getLegAngle(): Float = legAngle

    fun setLegAngle(value: Float) {
        legAngle = value
        invalidate()
    }

    fun getBallX(): Float = ballX

    fun setBallX(value: Float) {
        ballX = value
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        groundY = h * 0.8f
        stickmanX = w * 0.2f
        stickmanY = groundY - legLength - bodyLength - headRadius * 2
        ballX = stickmanX + 90f
        ballY = groundY - ballRadius
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(0f, groundY, width.toFloat(), groundY, paint)

        canvas.drawOval(
            ballX - ballRadius,
            groundY - ballRadius / 3,
            ballX + ballRadius,
            groundY + ballRadius / 3,
            shadowPaint
        )

        canvas.drawOval(
            stickmanX - 50f,
            groundY - 20f,
            stickmanX + 50f,
            groundY + 10f,
            shadowPaint
        )

        val headCenterY = stickmanY + headRadius
        canvas.drawCircle(stickmanX, headCenterY, headRadius, paint)

        val bodyStartY = stickmanY + headRadius * 2
        val bodyEndY = bodyStartY + bodyLength
        canvas.drawLine(stickmanX, bodyStartY, stickmanX, bodyEndY, paint)

        val armY = bodyStartY + 40f
        canvas.drawLine(stickmanX, armY, stickmanX - armLength, armY + 20f, paint)
        canvas.drawLine(stickmanX, armY, stickmanX + armLength, armY + 20f, paint)

        val hipY = bodyEndY

        canvas.drawLine(
            stickmanX,
            hipY,
            stickmanX - 40f,
            groundY,
            paint
        )

        canvas.save()
        canvas.rotate(legAngle, stickmanX, hipY)
        canvas.drawLine(
            stickmanX,
            hipY,
            stickmanX + 40f,
            groundY,
            paint
        )
        canvas.restore()

        canvas.drawCircle(ballX, ballY, ballRadius, fillPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && !isAnimating) {
            val dx = event.x - stickmanX
            val dy = event.y - (stickmanY + bodyLength)
            if (Math.abs(dx) < 100 && Math.abs(dy) < 200) {
                startKickAnimation()
            }
        }
        return true
    }

    private fun startKickAnimation() {
        isAnimating = true

        val legForward = ObjectAnimator.ofFloat(this, "legAngle", 0f, -60f)
        legForward.duration = 300

        val ballMove = ObjectAnimator.ofFloat(
            this,
            "ballX",
            ballX,
            width - ballRadius
        )
        ballMove.duration = 1000
        ballMove.interpolator = AccelerateDecelerateInterpolator()

        val legBack = ObjectAnimator.ofFloat(this, "legAngle", -60f, 0f)
        legBack.duration = 300

        val set = AnimatorSet()
        set.playSequentially(legForward, ballMove, legBack)
        set.start()
    }
}