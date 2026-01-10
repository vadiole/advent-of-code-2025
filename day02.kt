import java.io.File

// https://adventofcode.com/2025/day/2
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
            if (productIdString.length % 2 != 0) continue
            val centerIndex = productIdString.length / 2
            val leftPart = productIdString.substring(0, centerIndex)
            val rightPart = productIdString.substring(centerIndex)
            if (leftPart == rightPart) {
                invalidProductIdsSum += productId
            }
        }
    }
    println("output: $invalidProductIdsSum")
}
