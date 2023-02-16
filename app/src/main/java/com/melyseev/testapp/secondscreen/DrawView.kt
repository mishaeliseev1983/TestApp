package com.melyseev.testapp.secondscreen

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class DrawView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {
    var p: Paint
    var rect: Rect

    init {
        p = Paint()
        rect = Rect()
    }

    override fun onDraw(canvas: Canvas) {
        //canvas.drawARGB(80, 194, 197, 204)
        val startX = 50
        p.color = Color.BLACK
        p.strokeWidth = 2f
        canvas.drawLine(startX + 50f, 75f,  startX + 150f, 75f, p)
        p.strokeWidth = 1f
        canvas.drawLine(startX + 150f + 50 , 75f,  startX + 150f + 150 - 20, 75f, p)
        canvas.drawLine(startX + 150f + 150 + 20, 75f,  startX + 150f + 150 + 20 + 100 - 30 , 75f, p)

        rect = Rect( startX, 50, startX + 50, 50+50)
        p.color = Color.RED
        p.style = Paint.Style.STROKE;
        p.strokeWidth = 7f;
        canvas.drawRect(rect, p)

        p.color = Color.BLUE
        p.setStyle(Paint.Style.STROKE);
        rect.offset(startX + 100, 0);
        canvas.drawRect(rect, p)

        p.strokeWidth = 3f
        p.color = Color.RED
        canvas.drawCircle(startX + 300f, 75f, 20f, p)


        p.color = Color.YELLOW
        canvas.drawCircle(startX + 400f, 75f, 10f, p)


        p.color = Color.RED
        canvas.drawCircle(startX + 500f, 75f, 20f, p)
        p.strokeWidth = 7f
        canvas.drawCircle(startX + 500f, 75f, 40f, p)
        canvas.drawCircle(startX + 500f, 75f, 60f, p)


    }
}