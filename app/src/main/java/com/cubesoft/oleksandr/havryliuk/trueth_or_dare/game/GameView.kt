package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var players = mutableListOf<String>()
    private val colors = listOf(
        resources.getColor(R.color.tile),
        resources.getColor(R.color.amber),
        resources.getColor(R.color.indigo),
        resources.getColor(R.color.pink),
        resources.getColor(R.color.purple),
        resources.getColor(R.color.brown),
        resources.getColor(R.color.orange),
        resources.getColor(R.color.gray),
        resources.getColor(R.color.cyan),
        resources.getColor(R.color.red)
    )

    override fun onDraw(canvas: Canvas) {

        val x = width.toFloat() / 2
        val y = height.toFloat() / 2
        val radius = if (x > y) y else x

        val sweepAngel = 360.0f.div(players.size.toFloat())
        var startAngel = 0f

        if (players.isEmpty())
            players.add("")

        for ((i, name) in players.withIndex()) {
            drawSector(
                canvas,
                x, y,
                radius,
                startAngel, sweepAngel,
                colors[i]
            )

            drawText(
                canvas,
                x, y,
                startAngel + sweepAngel / 2f,
                name
            )

            startAngel += sweepAngel
        }

    }

    private fun drawSector(
        canvas: Canvas, x: Float, y: Float, radius: Float,
        startAngel: Float, sweepAngle: Float, color: Int
    ) {
        val path = Path()
        path.addCircle(x, y, radius, Path.Direction.CW)

        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = color
        paint.strokeWidth = 5f
        paint.style = Paint.Style.FILL

        val oval = RectF()

        oval.set(
            x - radius,
            y - radius,
            x + radius,
            y + radius
        )
        canvas.drawArc(oval, startAngel, sweepAngle, true, paint)
    }

    private fun drawText(
        canvas: Canvas, x: Float, y: Float, angel: Float, text: String
    ) {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.textSize = 40f
        paint.color = Color.WHITE
        paint.strokeWidth = 3f

        canvas.save()
        canvas.rotate(angel, x, y)
        canvas.drawText(text, x + 250f, y, paint)
        canvas.restore()
    }

    fun setPlayers(players: List<String>) {
        this.players = players as MutableList<String>
        requestLayout()
    }
}
