import java.io.File

//https://adventofcode.com/2025/day/9#part2
fun main() {
    val input = File("input.txt").readText()
    val coords = input.lines().map { line ->
        val (x, y) = line.split(",").map { it.toInt() }
        x to y
    }
    var maxRectSquare = 1L
    for (i in coords.indices) {
        val (x1, y1) = coords[i]
        for (j in i + 1..coords.lastIndex) {
            val (x2, y2) = coords[j]
            val (left, right) = minOf(x1, x2) to maxOf(x1, x2)
            val (top, bottom) = minOf(y1, y2) to maxOf(y1, y2)
            val square = (right - left + 1L) * (bottom - top + 1L)
            if (square > maxRectSquare) {
                val isValid = coords.zipWithNext().none { (testStart, testEnd) ->
                    val (testX1, textY1) = testStart
                    val (textX2, testY2) = testEnd
                    val testLeft = minOf(testX1, textX2)
                    val testRight = maxOf(testX1, textX2)
                    val testTop = minOf(textY1, testY2)
                    val testBottom = maxOf(textY1, testY2)
                    // aabb collision detection
                    left < testRight && right > testLeft && top < testBottom && bottom > testTop
                }
                if (isValid) {
                    maxRectSquare = square
                }
            }
        }
    }
    println("output: $maxRectSquare")
}
