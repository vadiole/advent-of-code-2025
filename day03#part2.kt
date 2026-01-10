import java.io.File
import kotlin.math.pow

// https://adventofcode.com/2025/day/3#part2
fun main() {
    val input = File("input.txt").readText()
    val batteryBanks = input.lines().map { line ->
        line.map { it.digitToInt() }
    }
    var totalJoltage = 0L
    for (batteryBank in batteryBanks) {
        val batteriesNeeded = 12
        var bankJoltage = 0L
        var batteryRangeStartIndex = 0
        for (batteryIndex in batteriesNeeded - 1 downTo 0) {
            val batteryRange = batteryBank.subList(batteryRangeStartIndex, batteryBank.size - batteryIndex)
            val battery = batteryRange.max()
            batteryRangeStartIndex = batteryRangeStartIndex + batteryRange.indexOf(battery) + 1
            bankJoltage += battery * 10.0.pow(batteryIndex).toLong()
        }
        totalJoltage += bankJoltage
    }
    println("output: $totalJoltage")
}
