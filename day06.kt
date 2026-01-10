import java.io.File

// https://adventofcode.com/2025/day/6
fun main() {
    val input = File("input.txt").readText()
    val spaceRegex = Regex("\\s+")
    val mathSheet = input.lines().dropLast(1)
        .map { line ->
            line
                .trim()
                .split(spaceRegex)
                .map { number ->
                    number.trim().toLong()
                }
        }
    val operations = input.lines().last()
        .split(spaceRegex)
        .map { it.trim() }
    var sum = 0L
    for (i in operations.indices) {
        var operationResult = mathSheet[0][i]
        val operation = when (operations[i]) {
            "+" -> { a: Long, b: Long -> a + b }
            "*" -> { a: Long, b: Long -> a * b }
            else -> error("Unknown operation: ${operations[i]}")
        }
        for (line in mathSheet.drop(1)) {
            val number = line[i]
            operationResult = operation(operationResult, number)
        }
        sum += operationResult
    }
    println("output: $sum")
}
