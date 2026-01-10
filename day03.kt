import java.io.File

// https://adventofcode.com/2025/day/3
fun main() {
    val input = File("input.txt").readText()
    val batteryBanks = input.lines().map { line ->
        line.map { it.digitToInt() }
    }
    var totalJoltage = 0
    for (batteryBank in batteryBanks) {
        val firstBatteryRange = batteryBank.dropLast(1)
        val firstBattery = firstBatteryRange.max()
        val firstBatteryIndex = batteryBank.indexOf(firstBattery)
        val secondBatteryRange = batteryBank.subList(firstBatteryIndex + 1, batteryBank.size)
        val secondsBattery = secondBatteryRange.max()
        val batteryJoltage = firstBattery * 10 + secondsBattery
        totalJoltage += batteryJoltage
    }
    println("output: $totalJoltage")
}
