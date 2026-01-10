import java.io.File

//https://adventofcode.com/2025/day/9
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
                maxRectSquare = square
            }
        }
    }
    println("output: $maxRectSquare")
}
