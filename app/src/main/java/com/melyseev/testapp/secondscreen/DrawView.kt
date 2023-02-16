package com.melyseev.testapp.secondscreen

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class DrawView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val startX = 50
    var p: Paint = Paint()
    private  var rect: Rect = Rect(startX, startX, startX + 50, startX + 50)

    override fun onDraw(canvas: Canvas) {

        p.color = Color.BLACK
        p.strokeWidth = 3f
        canvas.drawLine(startX + 50f, 75f, startX + 150f, 75f, p)
        canvas.drawLine(startX + 50f, 100f, startX + 150f, 100f, p)
        canvas.drawLine(startX + 50f, 50f, startX + 150f, 50f, p)
        canvas.drawLine(startX + 150f + 50, 75f, startX + 150f + 150 - 20, 75f, p)
        canvas.drawLine(startX + 150f + 150 + 20, 75f, startX + 150f + 150 + 20 + 100 - 30, 75f, p)

        p.color = Color.RED
        p.style = Paint.Style.STROKE
        p.strokeWidth = 8f
        canvas.drawRect(rect, p)


        p.color = Color.BLUE
        p.setStyle(Paint.Style.STROKE)
        rect.offset(startX + 100, 0)
        canvas.drawRect(rect, p)

        p.strokeWidth = 4f
        p.color = Color.RED
        canvas.drawCircle(startX + 300f, 75f, 20f, p)


        p.color = Color.GREEN
        canvas.drawCircle(startX + 400f, 75f, 10f, p)


        p.color = Color.RED
        p.strokeWidth = 9f
        canvas.drawCircle(startX + 500f, 75f, 20f, p)
        p.strokeWidth = 4f
        canvas.drawCircle(startX + 500f, 75f, 35f, p)

        p.strokeWidth = 4f
        p.textSize = 35f
        p.color = Color.BLUE
        p.isFakeBoldText = false
        val deltaX = 6
        canvas.drawText("A", 75f - deltaX, 150f, p)
        canvas.drawText("B", startX + 100 + 75f - deltaX, 150f, p)
        canvas.drawText("C", startX + 300f - deltaX, 150f, p)
        canvas.drawText("D", startX + 400f - deltaX, 150f, p)
        canvas.drawText("E", startX + 500f - deltaX, 150f, p)
    }
}