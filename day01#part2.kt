import java.io.File
import kotlin.math.absoluteValue

// https://adventofcode.com/2025/day/1#part2
fun main() {
    val input = File("input.txt").readText()
    val maxDirections = 100
    var position = 50
    var zeroHits = 0
    for (line in input.lines()) {
        val direction = line.take(1)
        val rotations = line.drop(1).toInt()
        val sign = when (direction) {
            "L" -> -1
            "R" -> 1
            else -> error("Unknown direction: $direction")
        }
        val newPosition = position + sign * rotations
        if (position != 0 && newPosition <= 0) {
            zeroHits++
        }
        zeroHits += newPosition.absoluteValue.div(maxDirections)
        position = newPosition.mod(maxDirections)
    }
    println("output: $zeroHits")
}
