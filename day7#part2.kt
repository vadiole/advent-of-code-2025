import java.io.File

// https://adventofcode.com/2025/day/7#part2
fun main() {
    val input = File("input.txt").readText()
    val diagram = input.lines().map { line ->
        line.toCharArray()
    }
    val beamStartIndex = diagram[0].indexOf('S')
    val beams = Array(diagram[0].size) { 0L }
    beams[beamStartIndex] = 1L
    for (row in diagram) {
        for (i in row.indices) {
            if (row[i] == '^') {
                beams[i - 1] += beams[i]
                beams[i + 1] += beams[i]
                beams[i] = 0
            }
        }
    }
    println("output: ${beams.sum()}")
}
