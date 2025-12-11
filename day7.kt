import java.io.File

// https://adventofcode.com/2025/day/7
fun main() {
    val input = File("input.txt").readText()
    val diagram = input.lines().map { line ->
        line.toCharArray()
    }
    val beamStartIndex = diagram[0].indexOf('S')
    val beamPositions = mutableSetOf(beamStartIndex)
    var beamsTotal = 0
    for (row in diagram) {
        for (i in row.indices) {
            if (row[i] == '^' && beamPositions.remove(i)) {
                beamsTotal++
                beamPositions.add(i - 1)
                beamPositions.add(i + 1)
            }
        }
    }
    println("output: $beamsTotal")
}
