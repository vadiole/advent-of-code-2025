import java.io.File
import kotlin.math.absoluteValue

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
            val width = (x2 - x1).absoluteValue + 1L
            val height = (y2 - y1).absoluteValue + 1L
            val square = width * height
            if (square > maxRectSquare) {
                maxRectSquare = square
            }
        }
    }
    println("output: $maxRectSquare")
}
