// https://adventofcode.com/2025/day/1
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
        position += sign * rotations
        position = position.mod(maxDirections)
        if (position == 0) {
            zeroHits++
        }
    }
    println("output: $zeroHits")
}
