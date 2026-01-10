import java.io.File

// https://adventofcode.com/2025/day/2#part2
fun main() {
    val input = File("input.txt").readText()
    val productIdRanges = input.split(",").map { range ->
        val (start, end) = range.split("-").map { it.toLong() }
        start..end
    }
    var invalidProductIdsSum = 0L
    for (range in productIdRanges) {
        for (productId in range) {
            val productIdString = productId.toString()
            for (partLength in 1..productIdString.length / 2) {
                val parts = productIdString.chunked(partLength)
                if (parts.all { it == parts[0] }) {
                    invalidProductIdsSum += productId
                    break
                }
            }
        }
    }
    println("output: $invalidProductIdsSum")
}
