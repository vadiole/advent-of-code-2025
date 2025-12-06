import java.io.File

// https://adventofcode.com/2025/day/5
fun main() {
    val input = File("input.txt").readText()
    val freshIngredientsRanges = input.substringBefore("\n\n")
        .lines()
        .map { line ->
            val (start, end) = line.split("-").map { it.toLong() }
            start..end
        }
    val availableIngredients = input
        .substringAfter("\n\n")
        .lines()
        .map { it.toLong() }
    var totalFreshIngredients = 0
    for (ingredient in availableIngredients) {
        for (range in freshIngredientsRanges) {
            if (ingredient in range) {
                totalFreshIngredients++
                break
            }
        }
    }
    println("output: $totalFreshIngredients")
}
