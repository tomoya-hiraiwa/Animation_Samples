package com.example.animationsample.dynamic_anim

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.animationsample.R

class ProgressCircleView(context: Context, attributeSet: AttributeSet): View(context,attributeSet)  {
    var nowValue = 0f
    private var stroke = 20f

    private val basePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.view_back)
        strokeWidth = stroke
        strokeCap = Paint.Cap.SQUARE
        style = Paint.Style.STROKE
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.view_progress)
        strokeWidth = stroke
        strokeCap = Paint.Cap.SQUARE
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val padding  = stroke/2f + 5f
        val progressValue = 360f/100f*nowValue
        val rectF = RectF(padding,padding,canvas!!.width - padding,canvas.height - padding)
        canvas.drawArc(rectF,-90f,360f,false,basePaint)
        canvas.drawArc(rectF,-90f,progressValue,false,progressPaint)
    }
}