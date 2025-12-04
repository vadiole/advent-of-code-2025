import java.io.File

// https://adventofcode.com/2025/day/4#part2
fun main() {
    val input = File("input.txt").readText()
    val diagram = input.lines().map { it.toCharArray() }
    val adjacentPositionOffsets = listOf(
        -1 to -1, -1 to +0, -1 to +1,
        +0 to -1,  /*   */  +0 to +1,
        +1 to -1, +1 to +0, +1 to +1,
    )
    val maxAdjacentRolls = 3
    var totalAccessibleRolls = 0
    while (true) {
        var removedRolls = 0
        for (i in diagram.indices) {
            for (j in diagram[i].indices) {
                if (diagram[i][j] != '@') continue
                var adjacentRolls = 0
                for ((offsetI, offsetJ) in adjacentPositionOffsets) {
                    val testI = i + offsetI
                    val testJ = j + offsetJ
                    if (testI !in diagram.indices) {
                        continue
                    }
                    if (testJ !in diagram[testI].indices) {
                        continue
                    }
                    if (diagram[testI][testJ] == '@') {
                        adjacentRolls++
                    }
                    if (adjacentRolls > maxAdjacentRolls) {
                        break
                    }
                }
                if (adjacentRolls <= maxAdjacentRolls) {
                    diagram[i][j] = '.'
                    removedRolls++
                }
            }
        }
        totalAccessibleRolls += removedRolls
        if (removedRolls == 0) {
            break
        }
    }
    println("output: $totalAccessibleRolls")
}
