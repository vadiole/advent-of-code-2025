import java.io.File

// https://adventofcode.com/2025/day/6#part2
fun main() {
    val input = File("input.txt").readText()
    val mathSheet = input.lines().dropLast(1)
        .map { line ->
            line.toCharArray()
        }
    val operations = input.lines().last().toCharArray()
    var operation: (Long, Long) -> Long = { a, b -> b }
    var operationResult = 0L
    var sum = 0L
    for (i in operations.indices) {
        operation = when (operations[i]) {
            '+' -> { a: Long, b: Long -> a + b }
            '*' -> { a: Long, b: Long -> a * b }
            else -> {
                val nextOperation = operations.getOrNull(i + 1)
                if (nextOperation == '+' || nextOperation == '*') {
                    sum += operationResult
                    operationResult = 0L
                    continue
                } else {
                    operation
                }
            }
        }
        val number = mathSheet.map { line -> line[i] }
            .joinToString("").trim()
            .toLong()
        operationResult = if (operationResult == 0L) {
            number
        } else {
            operation(operationResult, number)
        }
    }
    sum += operationResult
    println("output: $sum")
}
