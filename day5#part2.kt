import java.io.File

// https://adventofcode.com/2025/day/5#part2
fun main() {
    val input = File("input.txt").readText()
    var freshIngredientsRanges = input.substringBefore("\n\n")
        .lines()
        .map { line ->
            val (start, end) = line.split("-").map { it.toLong() }
            start..end
        }
    var freshIngredientsTotal = 0L
    var newLowerBound = 0L
    while (true) {
        freshIngredientsRanges = freshIngredientsRanges
            .filter { it.last >= newLowerBound }
        val range = freshIngredientsRanges
            .minByOrNull { it.first } ?: break
        val currentFirst = maxOf(range.first, newLowerBound)
        freshIngredientsTotal += range.last - currentFirst + 1 // 5..3 -> 3, 4, 5
        newLowerBound = range.last + 1
    }
    println("output: $freshIngredientsTotal")
}
