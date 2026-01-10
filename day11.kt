import java.io.File

// https://adventofcode.com/2025/day/11
fun main() {
    val input = File("input.txt").readText()
    val devices: Map<String, List<String>> = input.lines().map { line ->
        val start = line.substringBefore(":")
        val connections = line.substringAfter(": ").split(" ")
        start to connections
    }.associate { it }
    val totalConnections = visit("you", devices)
    println("output: $totalConnections")
}

private fun visit(
    device: String,
    devices: Map<String, List<String>>,
): Long {
    val connections = devices[device] ?: return 1L
    val result = connections.sumOf { deviceToVisit ->
        visit(deviceToVisit, devices)
    }
    return result
}
