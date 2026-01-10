import java.io.File

// https://adventofcode.com/2025/day/11#part2
fun main() {
    val input = File("input.txt").readText()
    val devices: Map<String, List<String>> = input.lines().map { line ->
        val start = line.substringBefore(":")
        val connections = line.substringAfter(": ").split(" ")
        start to connections
    }.associate { it }
    val cache = mutableMapOf<String, Long>()
    val totalConnections = visit("svr", devices, cache, ttsConnected = false, dacConnected = false)
    println("output: $totalConnections")
}

private fun visit(
    device: String,
    devices: Map<String, List<String>>,
    cache: MutableMap<String, Long>,
    ttsConnected: Boolean,
    dacConnected: Boolean,
): Long {
    val connections = devices[device] ?: return if (ttsConnected && dacConnected) 1 else 0
    val cacheKey = "$device-$ttsConnected-$dacConnected"
    val cached = cache[cacheKey]
    if (cached != null) {
        return cached
    }
    val newTtsConnected = ttsConnected || device == "fft"
    val newDacConnected = dacConnected || device == "dac"
    val result = connections.sumOf { deviceToVisit ->
        visit(deviceToVisit, devices, cache, newTtsConnected, newDacConnected)
    }
    cache[cacheKey] = result
    return result
}
